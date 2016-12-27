package greymerk.editor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import greymerk.editor.tools.ToolBox;
import greymerk.editor.worldgen.Cardinal;
import greymerk.editor.worldgen.Coord;
import greymerk.editor.worldgen.WorldEditor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EditorEventHandler {

	Map<EntityPlayer, ToolBox> boxes;
	
	public EditorEventHandler(){
		boxes = new HashMap<EntityPlayer, ToolBox>();
	}
	
	@SubscribeEvent
	public void interact(RightClickBlock event){
		
		EntityPlayer player = event.getEntityPlayer();
		World world = player.world;
		
		if(world.isRemote) return;
		if(event.getHand() != EnumHand.MAIN_HAND) return;
		
		EnumFacing face = event.getFace();
		if(face == null) return;
		
		WorldEditor editor = new WorldEditor(world);
		Random rand = new Random();
		
		BlockPos ePos = event.getPos();
		Coord pos = new Coord(ePos.getX(), ePos.getY(), ePos.getZ());
		
		ToolBox box = fetchBox(player);
		
		Cardinal dir = Cardinal.getDirFromFace(face);
		box.action(editor, rand, player, dir, pos);
	}
	
	private ToolBox fetchBox(EntityPlayer player){
		
		if(!boxes.containsKey(player)){
			boxes.put(player, new ToolBox());
		}
			
		return boxes.get(player);
	}
}
