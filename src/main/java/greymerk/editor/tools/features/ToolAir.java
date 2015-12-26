package greymerk.editor.tools.features;

import java.util.Random;

import greymerk.editor.tools.ITool;
import greymerk.editor.tools.ToolState;
import greymerk.editor.worldgen.BlockProvider;
import greymerk.editor.worldgen.Coord;
import greymerk.editor.worldgen.MetaBlock;
import greymerk.editor.worldgen.WorldEditor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;

public class ToolAir implements ITool {

	@Override
	public void onClick(WorldEditor editor, Random rand, EntityPlayer player, ToolState state, Coord pos) {
		MetaBlock block = new MetaBlock(Blocks.air);
		state.init(BlockProvider.METABLOCK, block);
		String msg = "Brush set to: Air";
		player.addChatComponentMessage(new ChatComponentText(msg));	
	}

}
