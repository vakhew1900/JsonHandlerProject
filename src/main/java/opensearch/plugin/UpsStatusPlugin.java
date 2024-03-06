package opensearch.plugin;

import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.node.NodeClient;
import org.opensearch.cluster.metadata.IndexNameExpressionResolver;
import org.opensearch.common.settings.ClusterSettings;
import org.opensearch.common.settings.IndexScopedSettings;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.settings.SettingsFilter;
import org.opensearch.plugins.ActionPlugin;
import org.opensearch.plugins.Plugin;
import org.opensearch.rest.RestController;
import org.opensearch.rest.RestHandler;

import java.util.List;
import java.util.function.Supplier;

import static java.util.Collections.singletonList;

public class UpsStatusPlugin extends Plugin implements ActionPlugin {


    @Override
    public List<RestHandler> getRestHandlers(final Settings settings,
                                              final RestController restController,
                                              final ClusterSettings clusterSettings,
                                              final IndexScopedSettings indexScopedSettings,
                                              final SettingsFilter settingsFilter,
                                              final IndexNameExpressionResolver indexNameExpressionResolver,
                                              final Supplier nodesInCluster) {

        return singletonList(new UpsStatusRestHandler());
    }


}
