package com.akryvtsun.booklab;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

public class App {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Snippet 48");
        shell.setLayout(new FillLayout());

        Menu bar = new Menu (shell, SWT.BAR);
        shell.setMenuBar (bar);
        MenuItem fileItem = new MenuItem (bar, SWT.CASCADE);
        fileItem.setText ("&File");
        MenuItem helpItem = new MenuItem (bar, SWT.CASCADE);
        helpItem.setText ("&Help");
        Menu submenu = new Menu (shell, SWT.DROP_DOWN);
        fileItem.setMenu (submenu);
        MenuItem item = new MenuItem (submenu, SWT.PUSH);
        item.addListener (SWT.Selection, e -> System.out.println ("Select All"));
        item.setText ("Select &All\tCtrl+A");
        item.setAccelerator (SWT.MOD1 + 'A');

        Image originalImage = null;
        FileDialog dialog = new FileDialog(shell, SWT.OPEN);
        dialog.setText("Open an image file or cancel");
        String string = dialog.open();
        if (string != null) {
            originalImage = new Image(display, string);
        }
        if (originalImage == null) {
            int width = 150, height = 200;
            originalImage = new Image(display, width, height);
            GC gc = new GC(originalImage);
            gc.fillRectangle(0, 0, width, height);
            gc.drawLine(0, 0, width, height);
            gc.drawLine(0, height, width, 0);
            gc.drawText("Default Image", 10, 10);
            gc.dispose();
        }

        final Image image = originalImage;
        final Point origin = new Point(0, 0);
        final Canvas canvas = new Canvas(shell, SWT.NO_BACKGROUND |
                SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);
        final ScrollBar hBar = canvas.getHorizontalBar();
        hBar.addListener(SWT.Selection, e -> {
            int hSelection = hBar.getSelection();
            int destX = -hSelection - origin.x;
            Rectangle rect = image.getBounds();
            canvas.scroll(destX, 0, 0, 0, rect.width, rect.height, false);
            origin.x = -hSelection;
        });
        final ScrollBar vBar = canvas.getVerticalBar();
        vBar.addListener(SWT.Selection, e -> {
            int vSelection = vBar.getSelection();
            int destY = -vSelection - origin.y;
            Rectangle rect = image.getBounds();
            canvas.scroll(0, destY, 0, 0, rect.width, rect.height, false);
            origin.y = -vSelection;
        });
        canvas.addListener(SWT.Resize, e -> {
            Rectangle rect = image.getBounds();
            Rectangle client = canvas.getClientArea();
            hBar.setMaximum(rect.width);
            vBar.setMaximum(rect.height);
            hBar.setThumb(Math.min(rect.width, client.width));
            vBar.setThumb(Math.min(rect.height, client.height));
            int hPage = rect.width - client.width;
            int vPage = rect.height - client.height;
            int hSelection = hBar.getSelection();
            int vSelection = vBar.getSelection();
            if (hSelection >= hPage) {
                if (hPage <= 0) hSelection = 0;
                origin.x = -hSelection;
            }
            if (vSelection >= vPage) {
                if (vPage <= 0) vSelection = 0;
                origin.y = -vSelection;
            }
            canvas.redraw();
        });
        canvas.addListener(SWT.Paint, e -> {
            GC gc = e.gc;
            gc.drawImage(image, origin.x, origin.y);
            Rectangle rect = image.getBounds();
            Rectangle client = canvas.getClientArea();
            int marginWidth = client.width - rect.width;
            if (marginWidth > 0) {
                gc.fillRectangle(rect.width, 0, marginWidth, client.height);
            }
            int marginHeight = client.height - rect.height;
            if (marginHeight > 0) {
                gc.fillRectangle(0, rect.height, client.width, marginHeight);
            }
        });

        Rectangle rect = image.getBounds();
        shell.setSize(1024, 780);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        originalImage.dispose();
        display.dispose();
    }
}
