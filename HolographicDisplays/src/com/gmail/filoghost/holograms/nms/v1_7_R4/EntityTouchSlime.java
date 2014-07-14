package com.gmail.filoghost.holograms.nms.v1_7_R4;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;

import com.gmail.filoghost.holograms.nms.interfaces.TouchSlime;
import com.gmail.filoghost.holograms.object.CraftHologram;
import com.gmail.filoghost.holograms.object.HologramBase;

import net.minecraft.server.v1_7_R4.EntitySlime;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.World;

public class EntityTouchSlime extends EntitySlime implements TouchSlime {

	private boolean lockTick;
	private CraftHologram parent;
	
	public EntityTouchSlime(World world) {
		super(world);
		super.persistent = true;
		super.boundingBox.a = 0.0;
		super.boundingBox.b = 0.0;
		super.boundingBox.c = 0.0;
		super.boundingBox.d = 0.0;
		super.boundingBox.e = 0.0;
		super.boundingBox.f = 0.0;
		a(0.0F, 0.0F);
		setSize(1);
		setInvisible(true);
	}
	
	@Override
	public void h() {
		// Checks every 20 ticks.
		if (ticksLived % 20 == 0) {
			// The slime dies without a vehicle.
			if (this.vehicle == null) {
				die();
			}
		}
		
		if (!lockTick) {
			super.h();
		}
	}
	
	@Override
	public void b(NBTTagCompound nbttagcompound) {
		// Do not save NBT.
	}
	
	@Override
	public boolean c(NBTTagCompound nbttagcompound) {
		// Do not save NBT.
		return false;
	}

	@Override
	public boolean d(NBTTagCompound nbttagcompound) {
		// Do not save NBT.
		return false;
	}
	
	@Override
	public void e(NBTTagCompound nbttagcompound) {
		// Do not save NBT.
	}
	
	@Override
	public boolean isInvulnerable() {
		/* 
		 * The field Entity.invulnerable is private.
		 * It's only used while saving NBTTags, but since the entity would be killed
		 * on chunk unload, we prefer to override isInvulnerable().
		 */
	    return true;
	}

	@Override
	public void setCustomName(String customName) {
		// Locks the custom name.
	}
	
	@Override
	public void setCustomNameVisible(boolean visible) {
		// Locks the custom name.
	}
	
	@Override
	public void makeSound(String sound, float volume, float pitch) {
	    // Remove sounds.
	}
	
	public void setLockTick(boolean lock) {
		lockTick = lock;
	}
	
	public void die() {
		setLockTick(false);
		super.die();
	}
	
	public HologramBase getParent() {
		return parent;
	}
	
	public CraftEntity getBukkitEntity() {
		if (super.bukkitEntity == null) {
			this.bukkitEntity = new CraftTouchSlime(this.world.getServer(), this);
	    }
		return this.bukkitEntity;
	}

	@Override
	public boolean isDeadNMS() {
		return super.dead;
	}
	
	@Override
	public void killEntityNMS() {
		die();
	}
	
	@Override
	public void setLocationNMS(double x, double y, double z) {
		super.setPosition(x, y, z);
	}

	@Override
	public CraftHologram getParentHologram() {
		return parent;
	}

	@Override
	public void setParentHologram(CraftHologram hologram) {
		this.parent = hologram;
	}

}
