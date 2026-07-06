// https://github.com/dreamhead/moco/tree/10f44fdb2d465ce27951e7b012e1ae59c8e13d89/moco-runner/src/main/java/com/github/dreamhead/moco/parser/model/ProxyContainer.java#L110-L126
public class TempClass {
        public final ProxyContainer build() {
            if (this.url != null && (this.from != null || this.to != null)) {
                throw new IllegalArgumentException("Proxy cannot be set in multiple mode");
            }

            if (this.url == null && (this.from == null || this.to == null)) {
                throw new IllegalArgumentException("Batch proxy needs both 'from' and 'to'");
            }

            ProxyContainer container = new ProxyContainer();
            container.url = url;
            container.from = from;
            container.to = to;
            container.failover = failover;
            container.playback = playback;
            return container;
        }

}