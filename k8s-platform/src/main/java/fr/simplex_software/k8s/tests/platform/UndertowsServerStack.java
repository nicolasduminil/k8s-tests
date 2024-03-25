package fr.simplex_software.k8s.tests.platform;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.*;
import software.amazon.awscdk.cdk.lambdalayer.kubectl.v24.*;
import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.eks.*;
import software.constructs.*;

import java.util.*;

public class UndertowsServerStack extends Stack
{
  private static final String VPC_ID = "UndertowServerStackVpcId";
  private static final String VPC_NAME = "UndertowServerStackVpcName";

  public UndertowsServerStack(final Construct scope, final String id)
  {
    super(scope, id);
  }

  public UndertowsServerStack(final Construct scope, final String id, final StackProps props)
  {
    super (scope, id, props);
    Vpc vpc = Vpc.Builder.create(this, VPC_ID).vpcName(VPC_NAME).build();
    Cluster eksCluster = Cluster.Builder
      .create(this, "Cluster")
      .clusterName("QuarkusEKSCluster")
      .vpc(vpc)
      .defaultCapacity(0)
      .clusterLogging(Arrays.asList(ClusterLoggingTypes.API, ClusterLoggingTypes.AUDIT, ClusterLoggingTypes.AUTHENTICATOR,
        ClusterLoggingTypes.CONTROLLER_MANAGER, ClusterLoggingTypes.SCHEDULER))
      .version(KubernetesVersion.V1_24)
      .outputClusterName(true)
      .outputConfigCommand(true)
      .outputMastersRoleArn(true)
      .kubectlLayer(new KubectlV24Layer(this, "kubectl"))
      .albController(AlbControllerOptions.builder().version(AlbControllerVersion.V2_4_1).build())
      .build();
  }
}
