package com.previmet.synop.ui;


public class Items {

    private String title;
    private int icon;
    private int selectedIcon;

    public Items() {

    }

    public Items(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public Items(String title, int icon, int selectedIcon) {
        this.title = title;
        this.icon = icon;
        this.selectedIcon = selectedIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(int selectedIcon) {
        this.selectedIcon = selectedIcon;
    }
}
