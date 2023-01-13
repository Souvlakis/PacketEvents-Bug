package cy.souvlaki.packetevents_bug;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.SimplePacketListenerAbstract;
import com.github.retrooper.packetevents.event.simple.PacketLoginSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.dependency.SoftDependency;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

@Plugin(name = "PacketEvents-Bug", version = "1.0.0")
@SoftDependency("ProtocolLib")
@SoftDependency("ViaVersion")
public class PeBugPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
        PacketEvents.getAPI().init();
        PacketEvents.getAPI().getEventManager().registerListener(new SimplePacketListenerAbstract() {

            @Override
            public void onPacketLoginSend(PacketLoginSendEvent event) {
                if (event.getPacketType() == PacketType.Login.Server.LOGIN_SUCCESS) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        });
    }
}
