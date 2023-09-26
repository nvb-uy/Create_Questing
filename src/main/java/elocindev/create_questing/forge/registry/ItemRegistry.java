package elocindev.create_questing.forge.registry;

import elocindev.create_questing.forge.CreateQuesting;
import elocindev.create_questing.forge.item.QuestBlueprint;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CreateQuesting.MODID);

    public static final RegistryObject<Item> BLUEPRINT = REGISTRY.register("blueprint", () -> new QuestBlueprint());

    public static void load() {}    
}
