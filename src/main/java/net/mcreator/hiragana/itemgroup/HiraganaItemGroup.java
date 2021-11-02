
package net.mcreator.hiragana.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.hiragana.block.ABlock;
import net.mcreator.hiragana.HiraganaModElements;

@HiraganaModElements.ModElement.Tag
public class HiraganaItemGroup extends HiraganaModElements.ModElement {
	public HiraganaItemGroup(HiraganaModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabhiragana") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(ABlock.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
