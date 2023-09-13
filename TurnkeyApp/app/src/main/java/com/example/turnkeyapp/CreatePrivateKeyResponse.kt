package com.example.turnkeyapp

data class CreatePrivateKeyResponse(
    val activity: Activity,
)

data class Activity(
    val id: String,
    val organizationId: String,
    val status: String,
    val type: String,
    val intent: Intent,
    val result: Result,
    val votes: List<Vote>,
    val fingerprint: String,
    val canApprove: Boolean,
    val canReject: Boolean,
    val createdAt: CreatedAt5,
    val updatedAt: UpdatedAt4,
)

data class Intent(
    val createOrganizationIntent: CreateOrganizationIntent,
    val createAuthenticatorsIntent: CreateAuthenticatorsIntent,
    val createUsersIntent: CreateUsersIntent,
    val createPrivateKeysIntent: CreatePrivateKeysIntent,
    val signRawPayloadIntent: SignRawPayloadIntent,
    val createInvitationsIntent: CreateInvitationsIntent,
    val acceptInvitationIntent: AcceptInvitationIntent,
    val createPolicyIntent: CreatePolicyIntent,
    val disablePrivateKeyIntent: DisablePrivateKeyIntent,
    val deleteUsersIntent: DeleteUsersIntent,
    val deleteAuthenticatorsIntent: DeleteAuthenticatorsIntent,
    val deleteInvitationIntent: DeleteInvitationIntent,
    val deleteOrganizationIntent: DeleteOrganizationIntent,
    val deletePolicyIntent: DeletePolicyIntent,
    val createUserTagIntent: CreateUserTagIntent,
    val deleteUserTagsIntent: DeleteUserTagsIntent,
    val signTransactionIntent: SignTransactionIntent,
    val createApiKeysIntent: CreateApiKeysIntent,
    val deleteApiKeysIntent: DeleteApiKeysIntent,
    val approveActivityIntent: ApproveActivityIntent,
    val rejectActivityIntent: RejectActivityIntent,
    val createPrivateKeyTagIntent: CreatePrivateKeyTagIntent,
    val deletePrivateKeyTagsIntent: DeletePrivateKeyTagsIntent,
    val createPolicyIntentV2: CreatePolicyIntentV2,
    val setPaymentMethodIntent: SetPaymentMethodIntent,
    val activateBillingTierIntent: ActivateBillingTierIntent,
    val deletePaymentMethodIntent: DeletePaymentMethodIntent,
    val createPolicyIntentV3: CreatePolicyIntentV3,
    val createApiOnlyUsersIntent: CreateApiOnlyUsersIntent,
    val updateRootQuorumIntent: UpdateRootQuorumIntent,
    val updateUserTagIntent: UpdateUserTagIntent,
    val updatePrivateKeyTagIntent: UpdatePrivateKeyTagIntent,
    val createAuthenticatorsIntentV2: CreateAuthenticatorsIntentV2,
    val acceptInvitationIntentV2: AcceptInvitationIntentV2,
    val createOrganizationIntentV2: CreateOrganizationIntentV2,
    val createUsersIntentV2: CreateUsersIntentV2,
    val createSubOrganizationIntent: CreateSubOrganizationIntent,
    val createSubOrganizationIntentV2: CreateSubOrganizationIntentV2,
    val updateAllowedOriginsIntent: UpdateAllowedOriginsIntent,
    val createPrivateKeysIntentV2: CreatePrivateKeysIntentV2,
    val updateUserIntent: UpdateUserIntent,
    val updatePolicyIntent: UpdatePolicyIntent,
    val setPaymentMethodIntentV2: SetPaymentMethodIntentV2,
    val createSubOrganizationIntentV3: CreateSubOrganizationIntentV3,
)

data class CreateOrganizationIntent(
    val organizationName: String,
    val rootEmail: String,
    val rootAuthenticator: RootAuthenticator,
    val rootUserId: String,
)

data class RootAuthenticator(
    val authenticatorName: String,
    val userId: String,
    val attestation: Attestation,
    val challenge: String,
)

data class Attestation(
    val id: String,
    val type: String,
    val rawId: String,
    val authenticatorAttachment: String,
    val response: Response,
    val clientExtensionResults: ClientExtensionResults,
)

data class Response(
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<String>,
    val authenticatorAttachment: String,
)

data class ClientExtensionResults(
    val appid: Boolean,
    val appidExclude: Boolean,
    val credProps: CredProps,
)

data class CredProps(
    val rk: Boolean,
)

data class CreateAuthenticatorsIntent(
    val authenticators: List<Authenticator>,
    val userId: String,
)

data class Authenticator(
    val authenticatorName: String,
    val userId: String,
    val attestation: Attestation2,
    val challenge: String,
)

data class Attestation2(
    val id: String,
    val type: String,
    val rawId: String,
    val authenticatorAttachment: String,
    val response: Response2,
    val clientExtensionResults: ClientExtensionResults2,
)

data class Response2(
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<String>,
    val authenticatorAttachment: String,
)

data class ClientExtensionResults2(
    val appid: Boolean,
    val appidExclude: Boolean,
    val credProps: CredProps2,
)

data class CredProps2(
    val rk: Boolean,
)

data class CreateUsersIntent(
    val users: List<User>,
)

data class User(
    val userName: String,
    val userEmail: String,
    val accessType: String,
    val apiKeys: List<ApiKey>,
    val authenticators: List<Authenticator2>,
    val userTags: List<String>,
)

data class ApiKey(
    val apiKeyName: String,
    val publicKey: String,
)

data class Authenticator2(
    val authenticatorName: String,
    val userId: String,
    val attestation: Attestation3,
    val challenge: String,
)

data class Attestation3(
    val id: String,
    val type: String,
    val rawId: String,
    val authenticatorAttachment: String,
    val response: Response3,
    val clientExtensionResults: Map<String, Any>,
)

data class Response3(
    val transports: List<Any?>,
)

data class CreatePrivateKeysIntent(
    val privateKeys: List<PrivateKey>,
)

data class PrivateKey(
    val privateKeyName: String,
    val curve: String,
    val privateKeyTags: List<String>,
    val addressFormats: List<String>,
)

data class SignRawPayloadIntent(
    val privateKeyId: String,
    val payload: String,
    val encoding: String,
    val hashFunction: String,
)

data class CreateInvitationsIntent(
    val invitations: List<Invitation>,
)

data class Invitation(
    val receiverUserName: String,
    val receiverUserEmail: String,
    val receiverUserTags: List<String>,
    val accessType: String,
    val senderUserId: String,
)

data class AcceptInvitationIntent(
    val invitationId: String,
    val userId: String,
    val authenticator: Authenticator3,
)

data class Authenticator3(
    val authenticatorName: String,
    val userId: String,
    val attestation: Attestation4,
    val challenge: String,
)

data class Attestation4(
    val id: String,
    val type: String,
    val rawId: String,
    val authenticatorAttachment: String,
    val response: Response4,
    val clientExtensionResults: ClientExtensionResults3,
)

data class Response4(
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<String>,
    val authenticatorAttachment: String,
)

data class ClientExtensionResults3(
    val appid: Boolean,
    val appidExclude: Boolean,
    val credProps: CredProps3,
)

data class CredProps3(
    val rk: Boolean,
)

data class CreatePolicyIntent(
    val policyName: String,
    val selectors: List<Selector>,
    val effect: String,
    val notes: String,
)

data class Selector(
    val subject: String,
    val operator: String,
    val target: String,
)

data class DisablePrivateKeyIntent(
    val privateKeyId: String,
)

data class DeleteUsersIntent(
    val userIds: List<String>,
)

data class DeleteAuthenticatorsIntent(
    val userId: String,
    val authenticatorIds: List<String>,
)

data class DeleteInvitationIntent(
    val invitationId: String,
)

data class DeleteOrganizationIntent(
    val organizationId: String,
)

data class DeletePolicyIntent(
    val policyId: String,
)

data class CreateUserTagIntent(
    val userTagName: String,
    val userIds: List<String>,
)

data class DeleteUserTagsIntent(
    val userTagIds: List<String>,
)

data class SignTransactionIntent(
    val privateKeyId: String,
    val unsignedTransaction: String,
    val type: String,
)

data class CreateApiKeysIntent(
    val apiKeys: List<ApiKey2>,
    val userId: String,
)

data class ApiKey2(
    val apiKeyName: String,
    val publicKey: String,
)

data class DeleteApiKeysIntent(
    val userId: String,
    val apiKeyIds: List<String>,
)

data class ApproveActivityIntent(
    val fingerprint: String,
)

data class RejectActivityIntent(
    val fingerprint: String,
)

data class CreatePrivateKeyTagIntent(
    val privateKeyTagName: String,
    val privateKeyIds: List<String>,
)

data class DeletePrivateKeyTagsIntent(
    val privateKeyTagIds: List<String>,
)

data class CreatePolicyIntentV2(
    val policyName: String,
    val selectors: List<Selector2>,
    val effect: String,
    val notes: String,
)

data class Selector2(
    val subject: String,
    val operator: String,
    val targets: List<String>,
)

data class SetPaymentMethodIntent(
    val number: String,
    val cvv: String,
    val expiryMonth: String,
    val expiryYear: String,
    val cardHolderEmail: String,
    val cardHolderName: String,
)

data class ActivateBillingTierIntent(
    val productId: String,
)

data class DeletePaymentMethodIntent(
    val paymentMethodId: String,
)

data class CreatePolicyIntentV3(
    val policyName: String,
    val effect: String,
    val condition: String,
    val consensus: String,
    val notes: String,
)

data class CreateApiOnlyUsersIntent(
    val apiOnlyUsers: List<ApiOnlyUser>,
)

data class ApiOnlyUser(
    val userName: String,
    val userEmail: String,
    val userTags: List<String>,
    val apiKeys: List<ApiKey3>,
)

data class ApiKey3(
    val apiKeyName: String,
    val publicKey: String,
)

data class UpdateRootQuorumIntent(
    val threshold: Long,
    val userIds: List<String>,
)

data class UpdateUserTagIntent(
    val userTagId: String,
    val newUserTagName: String,
    val addUserIds: List<String>,
    val removeUserIds: List<String>,
)

data class UpdatePrivateKeyTagIntent(
    val privateKeyTagId: String,
    val newPrivateKeyTagName: String,
    val addPrivateKeyIds: List<String>,
    val removePrivateKeyIds: List<String>,
)

data class CreateAuthenticatorsIntentV2(
    val authenticators: List<Authenticator4>,
    val userId: String,
)

data class Authenticator4(
    val authenticatorName: String,
    val challenge: String,
    val attestation: Attestation5,
)

data class Attestation5(
    val credentialId: String,
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<String>,
)

data class AcceptInvitationIntentV2(
    val invitationId: String,
    val userId: String,
    val authenticator: Authenticator5,
)

data class Authenticator5(
    val authenticatorName: String,
    val challenge: String,
    val attestation: Attestation6,
)

data class Attestation6(
    val credentialId: String,
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<String>,
)

data class CreateOrganizationIntentV2(
    val organizationName: String,
    val rootEmail: String,
    val rootAuthenticator: RootAuthenticator2,
    val rootUserId: String,
)

data class RootAuthenticator2(
    val authenticatorName: String,
    val challenge: String,
    val attestation: Attestation7,
)

data class Attestation7(
    val credentialId: String,
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<String>,
)

data class CreateUsersIntentV2(
    val users: List<User2>,
)

data class User2(
    val userName: String,
    val userEmail: String,
    val accessType: String,
    val apiKeys: List<ApiKey4>,
    val authenticators: List<Authenticator6>,
    val userTags: List<String>,
)

data class ApiKey4(
    val apiKeyName: String,
    val publicKey: String,
)

data class Authenticator6(
    val authenticatorName: String,
    val challenge: String,
    val attestation: Attestation8,
)

data class Attestation8(
    val credentialId: String,
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<Any?>,
)

data class CreateSubOrganizationIntent(
    val name: String,
    val rootAuthenticator: RootAuthenticator3,
)

data class RootAuthenticator3(
    val authenticatorName: String,
    val challenge: String,
    val attestation: Attestation9,
)

data class Attestation9(
    val credentialId: String,
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<String>,
)

data class CreateSubOrganizationIntentV2(
    val subOrganizationName: String,
    val rootUsers: List<RootUser>,
    val rootQuorumThreshold: Long,
)

data class RootUser(
    val userName: String,
    val userEmail: String,
    val apiKeys: List<ApiKey5>,
    val authenticators: List<Authenticator7>,
)

data class ApiKey5(
    val apiKeyName: String,
    val publicKey: String,
)

data class Authenticator7(
    val authenticatorName: String,
    val challenge: String,
    val attestation: Attestation10,
)

data class Attestation10(
    val credentialId: String,
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<Any?>,
)

data class UpdateAllowedOriginsIntent(
    val allowedOrigins: List<String>,
)

data class CreatePrivateKeysIntentV2(
    val privateKeys: List<PrivateKey2>,
)

data class PrivateKey2(
    val privateKeyName: String,
    val curve: String,
    val privateKeyTags: List<String>,
    val addressFormats: List<String>,
)

data class UpdateUserIntent(
    val userId: String,
    val userName: String,
    val userEmail: String,
    val userTagIds: List<String>,
)

data class UpdatePolicyIntent(
    val policyId: String,
    val policyName: String,
    val policyEffect: String,
    val policyCondition: String,
    val policyConsensus: String,
    val policyNotes: String,
)

data class SetPaymentMethodIntentV2(
    val paymentMethodId: String,
    val cardHolderEmail: String,
    val cardHolderName: String,
)

data class CreateSubOrganizationIntentV3(
    val subOrganizationName: String,
    val rootUsers: List<RootUser2>,
    val rootQuorumThreshold: Long,
    val privateKeys: List<PrivateKey3>,
)

data class RootUser2(
    val userName: String,
    val userEmail: String,
    val apiKeys: List<ApiKey6>,
    val authenticators: List<Authenticator8>,
)

data class ApiKey6(
    val apiKeyName: String,
    val publicKey: String,
)

data class Authenticator8(
    val authenticatorName: String,
    val challenge: String,
    val attestation: Attestation11,
)

data class Attestation11(
    val credentialId: String,
    val clientDataJson: String,
    val attestationObject: String,
    val transports: List<Any?>,
)

data class PrivateKey3(
    val privateKeyName: String,
    val curve: String,
    val privateKeyTags: List<String>,
    val addressFormats: List<String>,
)

data class Result(
    val createOrganizationResult: CreateOrganizationResult,
    val createAuthenticatorsResult: CreateAuthenticatorsResult,
    val createUsersResult: CreateUsersResult,
    val createPrivateKeysResult: CreatePrivateKeysResult,
    val createInvitationsResult: CreateInvitationsResult,
    val acceptInvitationResult: AcceptInvitationResult,
    val signRawPayloadResult: SignRawPayloadResult,
    val createPolicyResult: CreatePolicyResult,
    val disablePrivateKeyResult: DisablePrivateKeyResult,
    val deleteUsersResult: DeleteUsersResult,
    val deleteAuthenticatorsResult: DeleteAuthenticatorsResult,
    val deleteInvitationResult: DeleteInvitationResult,
    val deleteOrganizationResult: DeleteOrganizationResult,
    val deletePolicyResult: DeletePolicyResult,
    val createUserTagResult: CreateUserTagResult,
    val deleteUserTagsResult: DeleteUserTagsResult,
    val signTransactionResult: SignTransactionResult,
    val deleteApiKeysResult: DeleteApiKeysResult,
    val createApiKeysResult: CreateApiKeysResult,
    val createPrivateKeyTagResult: CreatePrivateKeyTagResult,
    val deletePrivateKeyTagsResult: DeletePrivateKeyTagsResult,
    val setPaymentMethodResult: SetPaymentMethodResult,
    val activateBillingTierResult: ActivateBillingTierResult,
    val deletePaymentMethodResult: DeletePaymentMethodResult,
    val createApiOnlyUsersResult: CreateApiOnlyUsersResult,
    val updateRootQuorumResult: Map<String, Any>,
    val updateUserTagResult: UpdateUserTagResult,
    val updatePrivateKeyTagResult: UpdatePrivateKeyTagResult,
    val createSubOrganizationResult: CreateSubOrganizationResult,
    val updateAllowedOriginsResult: Map<String, Any>,
    val createPrivateKeysResultV2: CreatePrivateKeysResultV2,
    val updateUserResult: UpdateUserResult,
    val updatePolicyResult: UpdatePolicyResult,
    val createSubOrganizationResultV3: CreateSubOrganizationResultV3,
)

data class CreateOrganizationResult(
    val organizationId: String,
)

data class CreateAuthenticatorsResult(
    val authenticatorIds: List<String>,
)

data class CreateUsersResult(
    val userIds: List<String>,
)

data class CreatePrivateKeysResult(
    val privateKeyIds: List<String>,
)

data class CreateInvitationsResult(
    val invitationIds: List<String>,
)

data class AcceptInvitationResult(
    val invitationId: String,
    val userId: String,
)

data class SignRawPayloadResult(
    val r: String,
    val s: String,
    val v: String,
)

data class CreatePolicyResult(
    val policyId: String,
)

data class DisablePrivateKeyResult(
    val privateKeyId: String,
)

data class DeleteUsersResult(
    val userIds: List<String>,
)

data class DeleteAuthenticatorsResult(
    val authenticatorIds: List<String>,
)

data class DeleteInvitationResult(
    val invitationId: String,
)

data class DeleteOrganizationResult(
    val organizationId: String,
)

data class DeletePolicyResult(
    val policyId: String,
)

data class CreateUserTagResult(
    val userTagId: String,
    val userIds: List<String>,
)

data class DeleteUserTagsResult(
    val userTagIds: List<String>,
    val userIds: List<String>,
)

data class SignTransactionResult(
    val signedTransaction: String,
)

data class DeleteApiKeysResult(
    val apiKeyIds: List<String>,
)

data class CreateApiKeysResult(
    val apiKeyIds: List<String>,
)

data class CreatePrivateKeyTagResult(
    val privateKeyTagId: String,
    val privateKeyIds: List<String>,
)

data class DeletePrivateKeyTagsResult(
    val privateKeyTagIds: List<String>,
    val privateKeyIds: List<String>,
)

data class SetPaymentMethodResult(
    val lastFour: String,
    val cardHolderName: String,
    val cardHolderEmail: String,
)

data class ActivateBillingTierResult(
    val productId: String,
)

data class DeletePaymentMethodResult(
    val paymentMethodId: String,
)

data class CreateApiOnlyUsersResult(
    val userIds: List<String>,
)

data class UpdateUserTagResult(
    val userTagId: String,
)

data class UpdatePrivateKeyTagResult(
    val privateKeyTagId: String,
)

data class CreateSubOrganizationResult(
    val subOrganizationId: String,
)

data class CreatePrivateKeysResultV2(
    val privateKeys: List<PrivateKey4>,
)

data class PrivateKey4(
    val privateKeyId: String,
    val addresses: List<Address>,
)

data class Address(
    val format: String,
    val address: String,
)

data class UpdateUserResult(
    val userId: String,
)

data class UpdatePolicyResult(
    val policyId: String,
)

data class CreateSubOrganizationResultV3(
    val subOrganizationId: String,
    val privateKeys: List<PrivateKey5>,
)

data class PrivateKey5(
    val privateKeyId: String,
    val addresses: List<Address2>,
)

data class Address2(
    val format: String,
    val address: String,
)

data class Vote(
    val id: String,
    val userId: String,
    val user: User3,
    val activityId: String,
    val selection: String,
    val message: String,
    val publicKey: String,
    val signature: String,
    val scheme: String,
    val createdAt: CreatedAt4,
)

data class User3(
    val userId: String,
    val userName: String,
    val userEmail: String,
    val accessType: String,
    val authenticators: List<Authenticator9>,
    val apiKeys: List<ApiKey7>,
    val userTags: List<String>,
    val createdAt: CreatedAt3,
    val updatedAt: UpdatedAt3,
)

data class Authenticator9(
    val transports: List<String>,
    val attestationType: String,
    val aaguid: String,
    val credentialId: String,
    val model: String,
    val credential: Credential,
    val authenticatorId: String,
    val authenticatorName: String,
    val createdAt: CreatedAt,
    val updatedAt: UpdatedAt,
)

data class Credential(
    val publicKey: String,
    val type: String,
)

data class CreatedAt(
    val seconds: String,
    val nanos: String,
)

data class UpdatedAt(
    val seconds: String,
    val nanos: String,
)

data class ApiKey7(
    val credential: Credential2,
    val apiKeyId: String,
    val apiKeyName: String,
    val createdAt: CreatedAt2,
    val updatedAt: UpdatedAt2,
)

data class Credential2(
    val publicKey: String,
    val type: String,
)

data class CreatedAt2(
    val seconds: String,
    val nanos: String,
)

data class UpdatedAt2(
    val seconds: String,
    val nanos: String,
)

data class CreatedAt3(
    val seconds: String,
    val nanos: String,
)

data class UpdatedAt3(
    val seconds: String,
    val nanos: String,
)

data class CreatedAt4(
    val seconds: String,
    val nanos: String,
)

data class CreatedAt5(
    val seconds: String,
    val nanos: String,
)

data class UpdatedAt4(
    val seconds: String,
    val nanos: String,
)
