/**
 *	rscplus, RuneScape Classic injection client to enhance the game
 *
 *	This file is part of rscplus.
 *
 *	rscplus is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	rscplus is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with rscplus.  If not, see <http://www.gnu.org/licenses/>.
 *
 *	Authors: see <https://github.com/OrN/rscplus>
 */

package Game;

public class Camera
{
	public static void init()
	{
		zoom = 750;
		rotation = 126;
		setDistance(9000);
	}

	public static void setDistance(int distance)
	{
		distance1 = distance + 100;
		distance2 = distance + 100;
		distance3 = 1;
		distance4 = distance;
	}

	public static void addRotation(int amount)
	{
		rotation = (rotation + amount) & 0xFF;
	}

	public static void addZoom(int amount)
	{
		if(amount == 0)
			return;

		zoom += amount;
		if(zoom > 1238)
			zoom = 1238;
		else if(zoom < 262)
			zoom = 262;

		// Crash fix for camera zoom, camera zoom cant be zero after (((zoom - 500) / 15) + 16
		//
		// GETSTATIC Game/Camera.zoom : I
		// SIPUSH -500
		// IADD
		// BIPUSH 15
		// IDIV
		// BIPUSH 16
		// IADD			<-- If zoom is -16 here, it's a gauranteed crash
		// IDIV			<-- Divide by zero if zoom is (-16 + 16)
		while(((zoom - 500) / 15) + 16 == 0)
			zoom -= 1;
	}

	public static int zoom;
	public static int rotation;
	public static int distance1;
	public static int distance2;
	public static int distance3;
	public static int distance4;
}