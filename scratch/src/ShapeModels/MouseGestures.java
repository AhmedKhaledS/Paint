package ShapeModels;

import java.awt.Point;
import java.util.ArrayList;

import org.w3c.dom.css.Rect;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
//import simplermouseinteraction.Resizer;

public class MouseGestures {

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	DoubleProperty inX, inY;
	DoubleProperty sizeX, sizeY;
	boolean resize;
	String state;
	ObservableList<Double> list;
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent t) {
			state = "lu";
			resize = false;
			inX = new SimpleDoubleProperty();
			inY = new SimpleDoubleProperty();
			sizeX = new SimpleDoubleProperty();
			sizeY = new SimpleDoubleProperty();
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			if (t.getSource() instanceof Line) {
				Line line = (Line) (t.getSource());
				double startX = line.getStartX();
				double startY = line.getStartY();
				double endX = line.getEndX();
				double endY = line.getEndY();
				if (Math.abs(startX - orgSceneX) < 40) {
					if (Math.abs(startY - orgSceneY) < 40) {
						resize = true;
						inX.setValue(startX);
						inY.setValue(startY);
						line.startXProperty().bind(inX);
						line.startYProperty().bind(inY);
						line.endXProperty().unbind();
						line.endYProperty().unbind();
					}
				} else if (Math.abs(endX - orgSceneX) < 40) {
					if (Math.abs(endY - orgSceneY) < 40) {
						resize = true;
						inX.setValue(endX);
						inY.setValue(endY);
						line.endXProperty().bind(inX);
						line.endYProperty().bind(inY);
						line.startXProperty().unbind();
						line.startYProperty().unbind();
					}
				}
			} else if (t.getSource() instanceof Ellipse) {
				Ellipse ell = (Ellipse) (t.getSource());
				boolean found = false;
				double aboveCenterX = ell.getCenterX();
				double aboveCenterY = ell.getCenterY() - ell.getRadiusY();
				// Resizer r1 = new Resizer(ell, aboveCenterX, aboveCenterY);
				if (Math.abs(orgSceneX - aboveCenterX) < 40) {
					if (Math.abs(orgSceneY - aboveCenterY) < 40) {
						resize = true;
						found = true;
						state = "ac";
						inX.setValue(ell.getRadiusX());
						ell.radiusXProperty().bind(inX);
						inY.setValue(ell.getRadiusY());
						ell.radiusYProperty().bind(inY);
					} else {
						resize = false;
					}
				}
				double belowCenterX = ell.getCenterX();
				double belowCenterY = ell.getCenterY() + ell.getRadiusY();
				if (Math.abs(orgSceneX - belowCenterX) < 20 && !found) {
					if (Math.abs(orgSceneY - belowCenterY) < 20) {
						resize = true;
						found = true;
						state = "bc";
						inX.setValue(ell.getRadiusX());
						ell.radiusXProperty().bind(inX);
						inY.setValue(ell.getRadiusY());
						ell.radiusYProperty().bind(inY);
					} else {
						resize = false;
					}
				}
				double rightToCenterX = ell.getCenterX() + ell.getRadiusX();
				double rightToCenterY = ell.getCenterY();
				if (Math.abs(orgSceneX - rightToCenterX) < 20 && !found) {
					if (Math.abs(orgSceneY - rightToCenterY) < 20) {
						resize = true;
						found = true;
						state = "rc";
						inX.setValue(ell.getRadiusX());
						ell.radiusXProperty().bind(inX);
						inY.setValue(ell.getRadiusY());
						ell.radiusYProperty().bind(inY);
					} else {
						resize = false;
					}
				}
				double leftToCenterX = ell.getCenterX() - ell.getRadiusX();
				double leftToCenterY = ell.getCenterY();
				if (Math.abs(orgSceneX - leftToCenterX) < 20 && !found) {
					if (Math.abs(orgSceneY - leftToCenterY) < 20) {
						resize = true;
						found = true;
						state = "lc";
						inX.setValue(ell.getRadiusX());
						ell.radiusXProperty().bind(inX);
						inY.setValue(ell.getRadiusY());
						ell.radiusYProperty().bind(inY);
					} else {
						resize = false;
					}
				}
				if (!found) {
					resize = false;
				}
			} else if (t.getSource() instanceof Circle) {

				Circle p = ((Circle) (t.getSource()));

				orgTranslateX = p.getCenterX();
				orgTranslateY = p.getCenterY();

			} else if (t.getSource() instanceof Rectangle) {
				Rectangle rect = (Rectangle) (t.getSource());
				if (Math.abs(rect.getX() - orgSceneX) < 10) {
					if (Math.abs(rect.getY() - orgSceneY) < 30) {
						resize = true;
						inX.setValue(t.getSceneX());
						inY.setValue(t.getSceneY());
						sizeX.setValue(rect.getWidth());
						sizeY.setValue(rect.getHeight());
						rect.xProperty().bind(inX);
						rect.yProperty().bind(inY);
						rect.widthProperty().bind(sizeX);
						rect.heightProperty().bind(sizeY);
					} else if (Math.abs(rect.getY() - (orgSceneY - rect.getHeight())) < 30) {
						state = "ld";
						resize = true;
						inX.setValue(t.getSceneX());
						inY.setValue(t.getSceneY());
						sizeX.setValue(rect.getWidth());
						sizeY.setValue(rect.getHeight());
						rect.xProperty().bind(inX);
						rect.widthProperty().bind(sizeX);
						rect.heightProperty().bind(sizeY);
					} else {
						resize = false;
					}
				} else {
					if (Math.abs(rect.getY() - orgSceneY) < 30) {
						if (Math.abs(rect.getX() - (orgSceneX - rect.getWidth())) < 30) {
							state = "ru";
							resize = true;
							inX.setValue(t.getSceneX());
							inY.setValue(t.getSceneY());
							sizeX.setValue(rect.getWidth());
							sizeY.setValue(rect.getHeight());
							rect.yProperty().bind(inY);
							rect.widthProperty().bind(sizeX);
							rect.heightProperty().bind(sizeY);
						}
					} else if (Math.abs(rect.getY() - (orgSceneY - rect.getHeight())) < 30) {
						if (Math.abs(rect.getX() - (orgSceneX - rect.getWidth())) < 30) {
							state = "rd";
							resize = true;
							sizeX.setValue(rect.getWidth());
							sizeY.setValue(rect.getHeight());
							rect.widthProperty().bind(sizeX);
							rect.heightProperty().bind(sizeY);
						}
					} else {
						resize = false;
					}
				}

			} else if (t.getSource() instanceof Polygon) {
				Polygon triangle = (Polygon) (t.getSource());
				list = triangle.getPoints();
				inX.setValue(-1);
				inY.setValue(-1);
				for (int i = 0; i < list.size() - 1; i++) {
					Double curX = list.get(i);
					Double curY = list.get(i + 1);
					if (Math.abs(orgSceneX - curX) < 30 && Math.abs(orgSceneY - curY) < 30) {
						resize = true;
						inX.setValue(i);
						inY.setValue(i + 1);
						break;
					}
				}
			} else if (t.getSource() instanceof Shape) {

				Shape p = ((Shape) (t.getSource()));
				orgTranslateX = p.getTranslateX();
				orgTranslateY = p.getTranslateY();

			}
			if (resize) {
				Shape p = ((Shape) (t.getSource()));
				// p.getParent().setCursor(Cursor.CROSSHAIR);
			}
		}
	};

	public void makeDraggable(Node node) {
		node.setOnMousePressed(circleOnMousePressedEventHandler);
		node.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		Shape shape = (Shape) (node);
		Color previous = (Color) shape.getStroke();
		node.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				shape.setStroke(Color.RED);
				shape.getStrokeDashArray().addAll(3.0, 7.0, 3.0, 7.0);
			}

		});
		node.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				shape.setStroke(previous);
				shape.getStrokeDashArray().clear();
			}

		});
		node.setOnMouseDragReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				shape.getParent().setCursor(Cursor.HAND);
			}

		});
	}

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {

			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;

			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;
			if (t.getSource() instanceof Line) {
				Line line = (Line) (t.getSource());
				if (resize) {
					// System.out.println(line.toString());
					inX.setValue(t.getSceneX());
					inY.setValue(t.getSceneY());
				} else {
					Node p = ((Node) (t.getSource()));

					p.setTranslateX(newTranslateX);
					p.setTranslateY(newTranslateY);
				}
			} else if (t.getSource() instanceof Ellipse) {
				Ellipse ell = (Ellipse) (t.getSource());
				if (resize) {
					if (state.equals("ac")) {
						inX.setValue(Math.abs(ell.getCenterX() - t.getSceneX()));
						inY.setValue(Math.abs(ell.getCenterY() - t.getSceneY()));
					}
				} else {
					Node p = ((Node) (t.getSource()));

					p.setTranslateX(newTranslateX);
					p.setTranslateY(newTranslateY);
				}
			} else if (t.getSource() instanceof Circle) {

				Circle p = ((Circle) (t.getSource()));

				p.setCenterX(newTranslateX);
				p.setCenterY(newTranslateY);

			} else if (t.getSource() instanceof Rectangle) {
				if (resize) {
					// System.out.println(inX.doubleValue());
					if (state.equals("lu")) {
						offsetX = orgSceneX - t.getSceneX();
						offsetY = orgSceneY - t.getSceneY();
						inX.setValue(t.getSceneX());
						inY.setValue(t.getSceneY());
						orgSceneX = t.getSceneX();
						orgSceneY = t.getSceneY();
						double x = sizeX.doubleValue();
						double y = sizeY.doubleValue();
						sizeX.setValue((x + offsetX));
						sizeY.setValue((y + offsetY));
					} else if (state.equals("ld")) {
						inX.setValue(t.getSceneX());
						offsetX = orgSceneX - t.getSceneX();
						// offsetY = orgSceneY - t.getSceneY();
						orgSceneX = t.getSceneX();
						orgSceneY = t.getSceneY();
						double x = sizeX.doubleValue();
						double y = sizeY.doubleValue();
						sizeX.setValue((x + offsetX));
						sizeY.setValue((y + offsetY));
					} else if (state.equals("ru")) {
						inY.setValue(t.getSceneY());
						offsetY = orgSceneY - t.getSceneY();
						orgSceneX = t.getSceneX();
						orgSceneY = t.getSceneY();
						double x = sizeX.doubleValue();
						double y = sizeY.doubleValue();
						sizeX.setValue((x + offsetX));
						sizeY.setValue((y + offsetY));
					} else if (state.equals("rd")) {
						orgSceneX = t.getSceneX();
						orgSceneY = t.getSceneY();
						double x = sizeX.doubleValue();
						double y = sizeY.doubleValue();
						sizeX.setValue((x + offsetX));
						sizeY.setValue((y + offsetY));
					}
				} else {
					Node p = ((Node) (t.getSource()));

					p.setTranslateX(newTranslateX);
					p.setTranslateY(newTranslateY);
				}
			} else if (t.getSource() instanceof Polygon) {
				if (resize) {
					if (!inX.equals(-1) && !inY.equals(-1)) {
						list.set(inX.intValue(), t.getSceneX());
						list.set(inY.intValue(), t.getSceneY());
					}
				} else {
					Node p = ((Node) (t.getSource()));

					p.setTranslateX(newTranslateX);
					p.setTranslateY(newTranslateY);
				}
			} else {

				Node p = ((Node) (t.getSource()));

				p.setTranslateX(newTranslateX);
				p.setTranslateY(newTranslateY);

			}

		}
	};

}