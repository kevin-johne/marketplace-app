package marketplace.core.secrets

import com.bitwarden.sdk.BitwardenClient
import com.bitwarden.sdk.BitwardenSettings
import io.github.cdimascio.dotenv.dotenv
import java.util.*

object SecretManager {
  private var client: BitwardenClient

  init {
    val bitwardenApiUrl = dotenv()["BITWARDEN_API_URL"]
    val bitwardenIdentityUrl = dotenv()["BITWARDEN_IDENTITY_URL"]
    val bitwardenStateFile = dotenv()["BITWARDEN_STATE_FILE"]
    val bitwardenAccessToken = dotenv()["BITWARDEN_ACCESS_TOKEN"]
    client = BitwardenClient(
      BitwardenSettings(
        bitwardenApiUrl,
        bitwardenIdentityUrl,
      )
    )
    client.auth().loginAccessToken(bitwardenAccessToken, bitwardenStateFile)
  }

  fun getSecret(secretId: String): String {
    return client.secrets().get(UUID.fromString(secretId)).value
  }
}