package net.darkhax.elysian.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackUtils {

    /**
     * Sets a stack compound to an ItemStack if it does not already have one.
     * @param stack: ItemStack having a tag set on it.
     */
    public static void prepareStackTag(ItemStack stack) {

        if (!stack.hasTagCompound()) {

            stack.setTagCompound(new NBTTagCompound());
        }
    }

    /**
     * Adds a value to the stack's tag.
     * @param stack: Stack being used.
     * @param tagName: Name of the tag.
     * @param value: Value being set, supports most data types that nbt supports.
     */
    public static void prepareStackNBT(ItemStack stack, String tagName, Object value) {

        prepareStackTag(stack);
        NBTTagCompound stackTag = stack.stackTagCompound;

        if (value instanceof String) {

            stackTag.setString(tagName, (String) value);
            return;
        }

        if (value instanceof Integer) {

            stackTag.setInteger(tagName, (Integer) value);
            return;
        }

        if (value instanceof Float) {

            stackTag.setFloat(tagName, (Float) value);
            return;
        }

        if (value instanceof Boolean) {

            stackTag.setBoolean(tagName, (Boolean) value);
            return;
        }

        if (value instanceof Double) {

            stackTag.setDouble(tagName, (Double) value);
            return;
        }

        if (value instanceof Long) {

            stackTag.setLong(tagName, (Long) value);
            return;
        }

        if (value instanceof Short) {

            stackTag.setShort(tagName, (Short) value);
            return;
        }

        if (value instanceof Byte) {

            stackTag.setByte(tagName, (Byte) value);
            return;
        }
    }
    
    
    public static boolean hasKey(ItemStack stack, String key){
    	NBTTagCompound stackTag = stack.stackTagCompound;
    	
    	if(stackTag == null)
    		return false;
    	
    	if(!stackTag.hasKey(key))
    		return false;
    	
    	return true;
    }
}