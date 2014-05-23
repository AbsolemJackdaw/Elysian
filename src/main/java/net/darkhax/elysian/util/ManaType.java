package net.darkhax.elysian.util;

public enum ManaType {

	FIRE, WATER, EARTH, AIR, LIGHT, DARKNESS, LIFE;

	public static ManaType getManaFromDamage(int i) {

		switch (i) {
		case 0:
			return FIRE;
		case 1:
			return WATER;
		case 2:
			return EARTH;
		case 3:
			return AIR;
		case 4:
			return LIGHT;
		case 5:
			return DARKNESS;
		case 6:
			return LIFE;
		default:
			break;
		}
		return null;
	}

	public static ManaType hexColorToType(int hexColor) {

		switch (hexColor) {
		case Reference.FIRE:
			return FIRE;
		case Reference.AIR:
			return AIR;
		case Reference.DARKNESS:
			return DARKNESS;
		case Reference.EARTH:
			return EARTH;
		case Reference.LIFE:
			return LIFE;
		case Reference.LIGHT:
			return LIGHT;
		case Reference.WATER:
			return WATER;
		}

		return null;
	}

	public static int manaTypeToColor(ManaType type) {

		switch (type) {
		case FIRE:
			return Reference.FIRE;
		case AIR:
			return Reference.AIR;
		case DARKNESS:
			return Reference.DARKNESS;
		case EARTH:
			return Reference.EARTH;
		case LIFE:
			return Reference.LIFE;
		case LIGHT:
			return Reference.LIGHT;
		case WATER:
			return Reference.WATER;
		}

		return 0;
	}

	public static byte manaTypeToByte(ManaType mana){
		return (byte) (mana == ManaType.FIRE ? 0 : 
			mana == ManaType.WATER ? 1 :
				mana == ManaType.AIR ? 2 :
					mana == ManaType.EARTH ? 3 : 
						mana == ManaType.LIGHT ? 4 :
							mana == ManaType.DARKNESS ? 5 : 6);
	}
}
