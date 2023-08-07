/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewComponent;

import ViewModels.MenuItemEnum;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author My laptop
 */
public class MenuItem {

    public JMenu jMenu;
    public HashMap<MenuItemEnum, JMenuItem> jMenuItems = new HashMap<>();

    public void addJMenuItem(MenuItemEnum itemEnum, JMenuItem item) {
        if (item != null) {
            jMenuItems.put(itemEnum, item);
        }
    }

    public MenuItem(JMenu _jMenu) {
        jMenu = _jMenu;
    }

    public JMenu getjMenu() {
        return jMenu;
    }

    public void addItemToMenu() {
        if (jMenu != null && !jMenuItems.isEmpty()) {
            for (JMenuItem jMenuItem : jMenuItems.values()) {
                jMenu.add(jMenuItem);
            }
        }
    }

    public HashMap<MenuItemEnum, JMenuItem> getMenuItems() {
        return jMenuItems;
    }
}
