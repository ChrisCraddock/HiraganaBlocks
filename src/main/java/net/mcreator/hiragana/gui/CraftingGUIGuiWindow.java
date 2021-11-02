
package net.mcreator.hiragana.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.hiragana.HiraganaMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class CraftingGUIGuiWindow extends ContainerScreen<CraftingGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = CraftingGUIGui.guistate;
	public CraftingGUIGuiWindow(CraftingGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 247;
		this.ySize = 227;
	}
	private static final ResourceLocation texture = new ResourceLocation("hiragana:textures/crafting_gui.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "n", 24, 10, -12829636);
		this.font.drawString(ms, "w-", 42, 10, -12829636);
		this.font.drawString(ms, "r-", 60, 10, -12829636);
		this.font.drawString(ms, "y-", 78, 10, -12829636);
		this.font.drawString(ms, "m-", 96, 10, -12829636);
		this.font.drawString(ms, "h-", 114, 10, -12829636);
		this.font.drawString(ms, "n-", 132, 10, -12829636);
		this.font.drawString(ms, "t-", 150, 10, -12829636);
		this.font.drawString(ms, "s-", 168, 10, -12829636);
		this.font.drawString(ms, "k-", 186, 10, -12829636);
		this.font.drawString(ms, "-a", 222, 19, -12829636);
		this.font.drawString(ms, "-i", 222, 37, -12829636);
		this.font.drawString(ms, "-u", 222, 55, -12829636);
		this.font.drawString(ms, "-e", 222, 73, -12829636);
		this.font.drawString(ms, "-o", 222, 91, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 6, this.guiTop + 91, 35, 20, new StringTextComponent("+1"), e -> {
			if (true) {
				HiraganaMod.PACKET_HANDLER.sendToServer(new CraftingGUIGui.ButtonPressedMessage(0, x, y, z));
				CraftingGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
