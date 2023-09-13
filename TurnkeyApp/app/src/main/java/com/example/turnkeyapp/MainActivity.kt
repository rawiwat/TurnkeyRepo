package com.example.turnkeyapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.turnkeyapp.ui.theme.TurnkeyAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Hex
import org.json.JSONObject
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Security
import java.security.Signature
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.spec.ECGenParameterSpec
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Security.addProvider(BouncyCastleProvider())
        val organizationId = "82d3f306-41ae-4fb3-b71b-8acb1ceda8fa"
        val timestampMs = System.currentTimeMillis().toString()
        val request = fetchCreatePrivateKeyWithTurnkey(
            this@MainActivity,
            timestampMs,
            organizationId,
            "key1",
            "tag1")

        setContent {
            TurnkeyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                println(request)
                                CoroutineScope(Dispatchers.Main).launch {
                                    createPrivateKeyPostRequest(request,this@MainActivity)
                                }
                            }
                        ) {
                         Text(text = "Create Private Key")
                        }
                    }
                }
            }
        }
    }
}

const val TURNKEY_API_SIGNATURE_SCHEME = "SIGNATURE_SCHEME_TK_API_P256"

fun Context.readFromAsset(fileName: String): String {
    var data: String
    this.assets.open(fileName).bufferedReader().use {
        data = it.readText()
    }
    return data
}

fun fetchCreatePrivateKeyWithTurnkey(
    context: Context,
    timestampMs: String,
    organizationId: String,
    privateKeyName: String,
    privateKeyTags: String
): String {
    return context.readFromAsset("CreatePrivateKey")
        .replace("<timestampMs>", timestampMs)
        .replace("<organizationId>", organizationId)
        .replace("<privateKeyName>", privateKeyName)
        .replace("<privateKeyTags>", privateKeyTags)

}

fun getPreEncodedHeader(
    context: Context,
    publicKey: String,
    signature: String,
    signatureScheme: String
):String {
    return context.readFromAsset("JsonHeader")
        .replace("<public_key>",publicKey)
        .replace("<signature>", signature)
        .replace("<signature_scheme>",signatureScheme)
}

/*fun generateSignature(privateKey: RSAPrivateKey, requestBody: String): ByteArray{
    try {
        /*val privateKeyBytes = Base64.getDecoder().decode(privateKeyStr)
        val privateKeySpec = PKCS8EncodedKeySpec(privateKeyBytes)
        println("privateKeyBytes = ${privateKeyBytes.joinToString(", ")}")
        println("privateKeySpec = ${privateKeySpec.encoded.joinToString(", ")}")
        val keyFactory = KeyFactory.getInstance("RSA")
        val privateKey: PrivateKey = keyFactory.generatePrivate(privateKeySpec)*/
        val signature = Signature.getInstance("SHA256withRSA")
        signature.initSign(privateKey)
        signature.update(requestBody.toByteArray())
        return signature.sign()
    } catch (e: Exception) {
        e.printStackTrace()
        throw e
    }
}*/

fun createJsonStringForHeader(publicKey: String, signature: String, signatureScheme: String): String {
    val json = JSONObject()
    json.put("public_key", publicKey)
    json.put("signature", signature)
    json.put("signature_scheme", signatureScheme)
    return json.toString()
}

fun convertToPrivateKeyRSA(privateKeyHex: String): PrivateKey {
    val privateKeyBytes = privateKeyHex.hexStringToByteArray()
    println("privateKeyBytes = ${privateKeyBytes.joinToString(", ")}")
    val keySpec = PKCS8EncodedKeySpec(privateKeyBytes)
    println("privateKeySpec = ${keySpec.encoded.joinToString(", ")}")
    val keyFactory = KeyFactory.getInstance("RSA")
    return keyFactory.generatePrivate(keySpec)
}

/*fun generateXStamp(
    postData: String,
    publicKeyString: String,
    privateKeyString: String
): String {
    try {
        val privateKeyBytes = privateKeyString.hexStringToByteArray()
        val keySpec = PKCS8EncodedKeySpec(privateKeyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        val privateKey = keyFactory.generatePrivate(keySpec)

        val signature = Signature.getInstance("SHA256withRSA")
        signature.initSign(privateKey)
        signature.update(postData.toByteArray(Charsets.UTF_8))
        val signedData = signature.sign()

        val signatureScheme = "RSA"
        val xStampData = "{\"publicKey\":\"$publicKeyString\",\"signature\":\"${byteArrayToBase64Url(signedData)}\",\"scheme\":\"$signatureScheme\"}"

        return byteArrayToBase64Url(xStampData.toByteArray(Charsets.UTF_8))
    } catch (e: Exception) {
        e.printStackTrace()
        throw e
    }
}*/

data class ApiStamp(val publicKey: Any, val signature: String, val scheme: Any)

fun generateStamp(message: String, apiKeyPair:ApiKeyPair): String {
    try {
        val sha256 = MessageDigest.getInstance("SHA-256")
        val hash = sha256.digest(message.toByteArray())

        val signature = Signature.getInstance("SHA256withECDSA")
        signature.initSign(apiKeyPair.privateKey)
        signature.update(hash)
        val sigBytes = signature.sign()
        val sigHex = sigBytes.joinToString("") { "%02x".format(it) }

        val stamp = ApiStamp(apiKeyPair.tkPublicKey, sigHex, TURNKEY_API_SIGNATURE_SCHEME)
        val jsonStamp = "{\"publicKey\":\"${stamp.publicKey}\",\"signature\":\"${stamp.signature}\",\"scheme\":\"${stamp.scheme}\"}"
        return Base64.getUrlEncoder().encodeToString(jsonStamp.toByteArray())

    } catch (e: Exception) {
        return e.message.toString()
    }
}

fun hexStringToPrivateKeyEC(hexString: String): PrivateKey {
    Security.addProvider(BouncyCastleProvider())
    val keyBytes = Hex.decode(hexString)
    val keySpec = PKCS8EncodedKeySpec(keyBytes)
    val keyFactory = KeyFactory.getInstance("EC")
    return keyFactory.generatePrivate(keySpec)
}

fun hexStringToPrivateKeyDSA(hexString: String): PrivateKey {
    val keyBytes = hexString.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    val keySpec = PKCS8EncodedKeySpec(keyBytes)
    val keyFactory = KeyFactory.getInstance("DSA")
    return keyFactory.generatePrivate(keySpec)
}

fun String.hexStringToByteArray() : ByteArray {
    val result = ByteArray(length / 2)

    for (i in 0 until length step 2) {
        val firstDigit = Character.digit(this[i], 16)
        val secondDigit = Character.digit(this[i + 1], 16)

        val byteValue = firstDigit shl 4 or secondDigit
        result[i / 2] = byteValue.toByte()
    }

    return result
}

data class ApiKeyPair(
    val tkPrivateKey: String,
    val tkPublicKey: String,
    val privateKey: ECPrivateKey,
    val publicKey: ECPublicKey
)

fun newTkApiKey(): ApiKeyPair {
    val keyGen = KeyPairGenerator.getInstance("EC")
    val ecSpec = ECGenParameterSpec("secp256r1")
    keyGen.initialize(ecSpec)
    val keyPair = keyGen.generateKeyPair()

    val tkPrivateKey = keyPair.private as java.security.interfaces.ECPrivateKey
    val tkPublicKey = keyPair.public as java.security.interfaces.ECPublicKey

    val tkPrivateKeyStr = tkPrivateKey.s.toString(16).padStart(64, '0')
    val tkPublicKeyStr = encodePublicKey(tkPublicKey)

    return ApiKeyPair(tkPrivateKeyStr, tkPublicKeyStr, tkPrivateKey, tkPublicKey)

}

fun ByteArray.toHexString(): String {
    return joinToString("") { "%02x".format(it) }
}

fun encodePublicKey(publicKey: ECPublicKey): String {
    val affineXBytes = publicKey.w.affineX.toByteArray()
    val point = if (affineXBytes.size >= 33) {
        affineXBytes.copyOfRange(1, 33) // Remove sign byte
    } else {
        // Handle case where the array is not of the expected size
        // You might want to return a default value or throw an exception
        // depending on your use case.
        ByteArray(32)
    }

    val prefix = if ((point[31] % 2).toByte() == 0.toByte()) "02" else "03"
    return prefix + point.toHexString()
}

fun publicKeyToHexString(publicKey: PublicKey): String {
    val keyFactory = KeyFactory.getInstance(publicKey.algorithm)
    val keySpec = X509EncodedKeySpec(publicKey.encoded)
    val encodedKey = keyFactory.generatePublic(keySpec).encoded

    val hexString = StringBuilder()
    for (byte in encodedKey) {
        val hex = Integer.toHexString(0xFF and byte.toInt())
        if (hex.length == 1) hexString.append('0')
        hexString.append(hex)
    }

    return hexString.toString()
}


