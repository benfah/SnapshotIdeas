package me.benfah.si.block;

import java.util.function.Consumer;

import me.benfah.si.block.entity.SoulFlaskBlockEntity;
import me.benfah.si.compat.IFlaskProvider;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SoulFlaskBlock extends AbstractGlassBlock implements BlockEntityProvider {

	public static final BooleanProperty LIT = Properties.LIT;
	public static final BooleanProperty NETHER = BooleanProperty.of("nether");

	public SoulFlaskBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(LIT, false).with(NETHER, false));
	}

	private VoxelShape getShape(BlockState state) {
		return createCuboidShape(4, 0, 3, 13, 13, 12);
	}

	public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return this.getShape(state);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return this.getShape(state);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new SoulFlaskBlockEntity();
	}

	public int getLuminance(BlockState state) {
		return (Boolean) state.get(LIT) ? super.getLuminance(state) : 0;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hit) {
		SoulFlaskBlockEntity blockEntity = (SoulFlaskBlockEntity) world.getBlockEntity(pos);
		IFlaskProvider provider = (IFlaskProvider) player;

		if (world.isClient) {
			return ActionResult.SUCCESS;
		} else {
			if (hand.equals(Hand.MAIN_HAND)) {

				if (player.getMainHandStack().getItem().equals(Items.FLINT_AND_STEEL)) {
					
					if(!world.dimension.isNether())
					{
						player.getMainHandStack().damage(1, player, (p) -> p.sendToolBreakStatus(hand));
						world.setBlockState(pos, state.with(LIT, true));
						return ActionResult.SUCCESS;
					}
					
					if (!blockEntity.isOccupied()
							|| provider.getActiveFlask() != null && provider.getActiveFlask().equals(pos)) {

						if (provider.getActiveFlask() != null && provider.canRespawnAtFlask()) {
							SoulFlaskBlockEntity oldBlockEntity = (SoulFlaskBlockEntity) world
									.getBlockEntity(provider.getActiveFlask());
							oldBlockEntity.clearActivePlayer();
							world.setBlockState(provider.getActiveFlask(),
									world.getBlockState(provider.getActiveFlask()).with(LIT, false));
						}

						player.getMainHandStack().damage(1, player, (p) -> p.sendToolBreakStatus(hand));
						world.setBlockState(pos, state.with(LIT, true).with(NETHER, true));

						blockEntity.setActivePlayer(player);
						provider.setActiveFlask(pos);
						player.addMessage(new TranslatableText("block.minecraft.bed.set_spawn"), false);

					}

					return ActionResult.SUCCESS;

				} else if (player.getMainHandStack().isEmpty() && player.isSneaking()) {
					if(!world.dimension.isNether())
					{
						world.setBlockState(pos, state.with(LIT, false));
						return ActionResult.SUCCESS;
					}
					
					if (blockEntity.isOccupied() && provider.getActiveFlask() != null
							&& provider.getActiveFlask().equals(pos)) {
						provider.setActiveFlask(null);
						blockEntity.clearActivePlayer();
						world.setBlockState(pos, state.with(LIT, false));
					}
				}

			}
			return ActionResult.PASS;
		}

	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(LIT).add(NETHER);
	}

}
