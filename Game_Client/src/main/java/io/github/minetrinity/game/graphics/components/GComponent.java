package io.github.minetrinity.game.graphics.components;


import io.github.minetrinity.game.graphics.Paintable;
import io.github.minetrinity.game.input.Controls;
import io.github.minetrinity.game.time.Tickable;

import java.awt.*;
import java.util.ArrayList;
import io.github.minetrinity.game.graphics.Window;

public class GComponent implements Paintable, Tickable {

    private Rectangle area;
    private int pright, pleft, ptop, pbot;
    private ArrayList<GComponent> gcomponents = new ArrayList<>();
    private GComponent parent;

    private boolean visible = true;
    private boolean ignorepadding = false;
    private boolean paintfirst = true;

    private boolean destroy = false;

    public GComponent(int x, int y, int width, int height) {
        this.area = new Rectangle(x,y,width,height);
    }

    public GComponent(Dimension size){
        this(0,0, size.width,size.height);
    }

    public void open() {
    }

    public void close(){
    }

    public void destroy() {
        destroy = true;
    }

    @Override
    public void tick() {

    }

    public void tickAll() {
        if (!visible) return;
        tick();
        for (int i = gcomponents.size() - 1; i >= 0; i--) {
            GComponent c = gcomponents.get(i);
            if (c == null) continue;
            c.tickAll();
        }
        for(int i = gcomponents.size() - 1; i >= 0; i--){
            if(gcomponents.get(i).destroy){
                gcomponents.remove(i);
            }
        }
    }

    @Override
    public void paint(Graphics g) {

    }

    public void paintAll(Graphics g) {
        if (!visible) return;
        paint(g);
        for (GComponent c : gcomponents) {
            if (c == null) continue;
            Graphics g2 = g.create(c.getX(), c.getY(), c.getWidth(), c.getHeight());
            c.paintAll(g2);
            g2.dispose();
        }
    }

    public void add(GComponent gcomponent) {
        add(gcomponent, gcomponents.size());
    }

    public void add(GComponent gcomponent, int index) {
        if (gcomponent == null) {
            gcomponents.add(null);
            return;
        }
        if (!ignorepadding) {
            if (gcomponent.area.x < pleft) gcomponent.area.x = pleft;
            if (gcomponent.area.x > area.width - pright) gcomponent.area.x = area.width - pright;
            if (gcomponent.area.y < ptop) gcomponent.area.y = ptop;
            if (gcomponent.area.y > area.height - pbot) gcomponent.area.y = area.height - pbot;
            if (gcomponent.area.x + gcomponent.area.width > area.width - pright) gcomponent.area.width = area.width - pright;
            if (gcomponent.area.y + gcomponent.area.height > area.height - pbot) gcomponent.area.height = area.height - pbot;
        }
        gcomponents.add(index, gcomponent);
        if (gcomponent.parent != null) gcomponent.parent.remove(gcomponent);
        gcomponent.parent = this;
        gcomponent.open();
    }

    public void remove(GComponent gcomponent) {
        gcomponent.close();
        gcomponents.remove(gcomponent);
        gcomponent.parent = null;
    }

    public ArrayList<GComponent> getComponents() {
        return gcomponents;
    }

    public GComponent getParent() {
        return parent;
    }

    protected void setParent(GComponent parent) {
        this.parent = parent;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setIgnorepadding(boolean ignorepadding) {
        this.ignorepadding = ignorepadding;
    }

    public boolean isIgnoreingPadding() {
        return ignorepadding;
    }

    public boolean isPointInBounds(Point p) {
        return area.contains(p);
    }

    public boolean isMouseOver() {
        return isPointInBounds(Controls.getMouseLocation());
    }

    public boolean isLeftClicked(){
        return isClicked(1);
    }

    public boolean isClicked(int mouseButton){
        return isMouseOver() && Controls.isMousePressed(mouseButton);
    }

    public int getAbsoluteWidthInWindow() {
        GComponent comp = this;
        int width = this.area.x;
        while(comp.parent != null){
            width += comp.parent.area.x;
            comp = comp.parent;
        }
        return width;
    }

    public int getAbsoluteHeightInWindow() {
        GComponent comp = this;
        int height = this.area.y;
        while(comp.parent != null){
            height += comp.parent.area.y;
            comp = comp.parent;
        }
        return height;
    }

    public int getAbsoluteHeight(){
        return getAbsoluteHeightInWindow() + Window.getInstance().getY();
    }

    public int getAbsoluteWidth(){
        return getAbsoluteWidthInWindow() + Window.getInstance().getWidth();
    }

    public int getPaddingRight() {
        return pright;
    }

    public void setPaddingRight(int pright) {
        if (pleft + pright > area.width) return;
        this.pright = pright;
    }

    public int getPaddingLeft() {
        return pleft;
    }

    public void setPaddingLeft(int pleft) {
        if (pleft + pright > area.width) return;
        this.pleft = pleft;
    }

    public int getPaddingTop() {
        return ptop;
    }

    public void setPaddingTop(int ptop) {
        if (pbot + ptop > area.height) return;
        this.ptop = ptop;
    }

    public int getPaddingBottom() {
        return pbot;
    }

    public void setPaddingBottom(int pbot) {
        if (pbot + ptop > area.height) return;
        this.pbot = pbot;
    }

    public int getX() {
        return area.x;
    }

    public int getY() {
        return area.y;
    }

    public int getWidth() {
        return area.width;
    }

    public int getHeight() {
        return area.height;
    }

    public void setX(int x) {
        this.area.x = x;
    }

    public void setY(int y) {
        this.area.y = y;
    }

    public void setWidth(int width) {
        this.area.width = width;
    }

    public void setHeight(int height) {
        this.area.height = height;
    }

    public void setSize(Dimension d){
        area.setSize(d);
    }

    public Dimension getSize(){
        return area.getSize();
    }

    public ArrayList<GComponent> getChildren() {
        return gcomponents;
    }
}
