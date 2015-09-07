package icbmrl.sentry.platform.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.common.util.ForgeDirection;

import icbmrl.core.common.lib.ModInfo;
import icbmrl.sentry.interfaces.IEnergyTurret;
import icbmrl.sentry.interfaces.IEnergyWeapon;
import icbmrl.sentry.interfaces.IWeaponProvider;
import icbmrl.sentry.platform.TileTurretPlatform;
import icbmrl.sentry.turret.block.TileTurret;
import org.lwjgl.opengl.GL11;
import scala.Unit;

public class GuiTurretPlatform extends GuiContainerBase
{
    public static final ResourceLocation TERMINAL_TEXTURE = new ResourceLocation(ModInfo.DOMAIN, ModInfo.GUI_PATH + "gui_platform_terminal.png");

    private TileTurretPlatform tile;

    public GuiTurretPlatform(EntityPlayer player, TileTurretPlatform tile)
    {
        super(new ContainerTurretPlatform(player.inventory, tile));
        this.tile = tile;
    }

    /** Draw the foreground layer for the GuiContainer (everything in front of the items) */
    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(tile.getInvName(), 52, 6, 4210752);

        // TODO: Add different directions in the future.
        TileTurret turret = tile.getTurret(ForgeDirection.UP);

        if (turret != null)
        {
            //TODO: re-add when rotation is implemented for sentries
            //fontRenderer.drawString("Position: " + ForgeDirection.UP, 8, 20, 4210752); 

            if (turret.getTurret() instanceof IEnergyContainer && ((IEnergyContainer) turret.getTurret()).getEnergyCapacity(ForgeDirection.UNKNOWN) > 0)
            {
                fontRenderer.drawString(EnumColor.BRIGHT_GREEN + "Energy", 8, 30, 4210752);
                renderUniversalDisplay(8, 40, ((IEnergyContainer) turret.getTurret()).getEnergy(ForgeDirection.UNKNOWN), ((IEnergyContainer) turret.getTurret()).getEnergyCapacity(ForgeDirection.UNKNOWN), mouseX, mouseY, Unit.JOULES, true);
            }
            if (turret.getTurret() instanceof IEnergyTurret)
            {
                fontRenderer.drawString(EnumColor.BRIGHT_GREEN + "Per Tick Cost", 8, 50, 4210752);
                renderUniversalDisplay(8, 60, ((IEnergyTurret) turret.getTurret()).getRunningCost(), mouseX, mouseY, Unit.JOULES, true);
            }
            if (turret.getTurret() instanceof IWeaponProvider && ((IWeaponProvider) turret.getTurret()).getWeaponSystem() instanceof IEnergyWeapon)
            {
                fontRenderer.drawString(EnumColor.BRIGHT_GREEN + "Weapon Cost", 8, 75, 4210752);
                renderUniversalDisplay(8, 85, ((IEnergyWeapon) ((IWeaponProvider) turret.getTurret()).getWeaponSystem()).getEnergyPerShot(), mouseX, mouseY, Unit.JOULES, true);
            }
        }
    }

    /** Draw the background layer for the GuiContainer (everything behind the items) */
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int x, int y)
    {
        super.drawGuiContainerBackgroundLayer(par1, x, y);

        this.mc.renderEngine.bindTexture(TERMINAL_TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        // drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize,
        // this.ySize);

        for (int xSlot = 0; xSlot < 4; xSlot++)
        {
            for (int ySlot = 0; ySlot < 3; ySlot++)
            {
                this.drawSlot(95 + 18 * xSlot - 1, 18 + 18 * ySlot - 1);
            }
        }

        for (int xSlot = 0; xSlot < 4; xSlot++)
        {
            this.drawSlot(95 + 18 * xSlot - 1, 89);
        }
    }
}