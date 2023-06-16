package org.example;

import lombok.ToString;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@ToString
public class ToStringExample {
    private static final int STATIC_VAR = 10;
    private String name;
    private Shape shape = new Square(5, 10);
    private String[] tags;
    @ToString.Exclude private int id;

    public String getName() {
        return this.name;
    }

    @ToString(callSuper=true, includeFieldNames=true)
    public static class Square implements Shape {
        private final int width, height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public Rectangle getBounds() {
            return null;
        }

        @Override
        public Rectangle2D getBounds2D() {
            return null;
        }

        @Override
        public boolean contains(double x, double y) {
            return false;
        }

        @Override
        public boolean contains(Point2D p) {
            return false;
        }

        @Override
        public boolean intersects(double x, double y, double w, double h) {
            return false;
        }

        @Override
        public boolean intersects(Rectangle2D r) {
            return false;
        }

        @Override
        public boolean contains(double x, double y, double w, double h) {
            return false;
        }

        @Override
        public boolean contains(Rectangle2D r) {
            return false;
        }

        @Override
        public PathIterator getPathIterator(AffineTransform at) {
            return null;
        }

        @Override
        public PathIterator getPathIterator(AffineTransform at, double flatness) {
            return null;
        }
    }
}