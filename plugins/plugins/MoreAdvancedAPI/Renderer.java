package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.entity.Player;
import org.bukkit.map.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Renderer extends MapRenderer {
    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {

        //Add colored pixels onto the map
//        mapCanvas.setPixel(10, 10, MapPalette.RED);
//
//        mapCanvas.setPixel(50, 50, MapPalette.PALE_BLUE);
//
//        for (int x = 25; x < 50; x++) { //X axis ROW
//            for (int y = 25; y < 50; y++) { //Y axis COLUMN
//                mapCanvas.setPixel(x, y, MapPalette.LIGHT_GREEN);
//            }
//        }

        //Puts text onto map
        //mapCanvas.drawText(25, 50, MinecraftFont.Font, "Look up at the sky");

        try {
            BufferedImage image = ImageIO.read(new URL("https://cdn.discordapp.com/attachments/435535294129897481/1307530835586580551/icon.png?ex=673aa47b&is=673952fb&hm=962919f6e7a6f810fe6021f1acb215d881d8df5ae7268cfa1ea32dadf3aa6aae&"));
            mapCanvas.drawImage(20, 20, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//PUT THIS IN THE MAIN CLASS
//@EventHandler
//public void onMapInitialize(MapInitializeEvent e) {
//
//    MapView view = e.getMap();
//
//    for (MapRenderer renderer : view.getRenderers()) { //Makes a nice clean map
//        view.removeRenderer(renderer);
//    }
//
//    view.addRenderer(new Renderer());
//}