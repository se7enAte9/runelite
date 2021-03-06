package net.runelite.client.plugins.theatre.timers;

import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.client.plugins.theatre.TheatrePlugin;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;
import javax.inject.Inject;
import java.awt.*;
import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class RoomTimer extends Overlay
{

	private final Client client;

	private final TheatrePlugin plugin;

	private final PanelComponent panelComponent = new PanelComponent();


	@Inject
	public RoomTimer (Client client, TheatrePlugin plugin) 
	{
		super (plugin);

		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
		setPriority(OverlayPriority.HIGH);

		this.client = client;
		this.plugin = plugin;

		getMenuEntries().add(new OverlayMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "TOB Timer Overlay"));
	}


	@Override
	public Dimension render(Graphics2D graphics) 
	{
		panelComponent.getChildren().clear();

		Player local = client.getLocalPlayer();
		if (local == null || local.getName() == null)
			return null;

		switch (plugin.getRoom()) 
		{
			case MAIDEN:
				plugin.getMaidenHandler().render(graphics);
				break;
			case BLOAT:
				plugin.getBloatHandler().render(graphics);
				break;
			case NYLOCAS:
				plugin.getNyloHandler().render(graphics);
				break;
			case SOTETSEG:
				plugin.getSotetsegHandler().render(graphics);
				break;
			case XARPUS:
				plugin.getXarpusHandler().render(graphics);
				break;
			case VERSIK:
				plugin.getVerzikHandler().render(graphics);
				break;
			default:
				break;
		}

		panelComponent.getChildren().add(TitleComponent.builder().text("Room Timer").color(Color.WHITE).build());

		return panelComponent.render(graphics);
	}
}