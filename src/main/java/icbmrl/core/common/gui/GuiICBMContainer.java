package icbmrl.core.common.gui;

import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import icbmrl.core.common.lib.ModInfo;

public abstract class GuiICBMContainer extends GuiContainerBase
{
    public static final ResourceLocation ICBM_EMPTY_TEXTURE = new ResourceLocation(ModInfo.DOMAIN, ModInfo.GUI_PATH + "gui_container.png");

    public GuiICBMContainer(Container container)
    {
        super(container);
        this.baseTexture = ICBM_EMPTY_TEXTURE;
    }
}
