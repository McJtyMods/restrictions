package mcjty.restrictions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public abstract class GenericBlock extends Block {

    public static final DirectionProperty FACING = DirectionProperty.create("facing");

    public GenericBlock(String name) {
        this(name, Material.IRON);
    }

    public GenericBlock(String name, Material material) {
        super(Properties.create(material).hardnessAndResistance(2.0f).sound(SoundType.METAL));
        setRegistryName(name);
//        setDefaultState(blockState.getBaseState().withProperty(FACING, Direction.NORTH));
//        setCreativeTab(Restrictions.setup.getTab());
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState p_getHarvestTool_1_) {
        return ToolType.PICKAXE;
    }

    @Override
    public int getHarvestLevel(BlockState p_getHarvestLevel_1_) {
        return 0;
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean what) {
        checkRedstone(world, pos);
    }

    protected void checkRedstone(World world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof GenericTileEntity) {
            int powered = world.getRedstonePowerFromNeighbors(pos); //TODO: check
            GenericTileEntity pusher = (GenericTileEntity) te;
            pusher.setPower(powered);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        world.setBlockState(pos, state.with(FACING, getFacingFromEntity(pos, entity)), 2);
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
        return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
