/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package compute;

// [START compute_firewall_delete]

import com.google.cloud.compute.v1.FirewallsClient;
import com.google.cloud.compute.v1.GlobalOperationsClient;
import com.google.cloud.compute.v1.Operation;
import java.io.IOException;
import java.util.UUID;

public class DeleteFirewallRule {

  public static void main(String[] args) throws IOException {
    // TODO(developer): Replace these variables before running the sample
    // project: project ID or project number of the Cloud project you want to use.
    // firewallRuleName: name of the firewall rule you want to delete.
    String project = "your-project-id";
    String firewallRuleName = "firewall-rule-name-" + UUID.randomUUID();
    deleteFirewallRule(project, firewallRuleName);
  }


  // Deletes a firewall rule from the project.
  public static void deleteFirewallRule(String project, String firewallRuleName)
      throws IOException {
    /* Initialize client that will be used to send requests. This client only needs to be created
       once, and can be reused for multiple requests. After completing all of your requests, call
       the `firewallsClient.close()` method on the client to safely
       clean up any remaining background resources. */
    try (FirewallsClient firewallsClient = FirewallsClient.create();
        GlobalOperationsClient operationsClient = GlobalOperationsClient.create()) {

      Operation operation = firewallsClient.delete(project, firewallRuleName);
      operationsClient.wait(project, operation.getName());
    }
  }
}
// [END compute_firewall_delete]
