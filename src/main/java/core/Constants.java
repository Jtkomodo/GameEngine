package main.java.core;

import org.joml.Vector4f;

public final class Constants {

	public static Vector4f BLACK = new Vector4f((0x00),(0x00),(0x00),0xff);
	public static Vector4f WHITE = new Vector4f(0xff,0xff,0xff,0xff);
	public static Vector4f GREEN=new Vector4f(0,0xff,0,0xff);
	public static Vector4f BLUE = new Vector4f((0x00),(0x00),(0xff),0xff);
	public static Vector4f YELLOW=new Vector4f(0xff,0xff,0x00,0xff);
	public static Vector4f RED=new Vector4f(0xff,0x00,0x00,0xff);
	public static Vector4f TEST_COLOR = new Vector4f(0xfb,0xad,0xfe,0x7f);
	//A7 58 55 90
	//7B C9 C3 9d
	public static Vector4f COL_COLOR_RED = new Vector4f(0xA7,0x58,0x55,0x90);
	public static Vector4f COL_COLOR_BLUE = new Vector4f(0x7b,0xc9,0xc3,0x9d);
	//22 F8 B6
	public static Vector4f BAR_COLOR_GREEN = new Vector4f(0x22,0xf8,0xb6,0xff);
	public static Vector4f BAR_COLOR_RED = new Vector4f(0xCD,0x20,0x1e,0xff);
	//FE 59 36
	//FE E8 36
	public static Vector4f BAR_COLOR_ORANGE=new Vector4f(0xfe,0xd6,0x35,0xff);
	public static Vector4f BAR_COLOR_YELLOW=new Vector4f(0xfe,0xe8,0x46,0xff);

	public static Vector4f DEFAULT_COLOR = new Vector4f(1,1,1,1);
	public static Vector4f SPRITE_NOT_SELECTED_COLOR=new Vector4f(0x64,0x64,0x64,0xff);


}
