package ca.uqac;

import org.jdom2.Element;

import java.util.List;

public class Q4 {

    public static class Point
    {
        double X;
        double Y;
    }

    public static void findLineEndings(List<Element> events) {
        /**
         * TODO
         */

        //We're assuming all events are in chronological order
        Element prevEvent = null;
        Element prevPrevEvent = null;



        for (Element currentEvent : events) {

            boolean line = false;

            if(prevEvent != null && prevPrevEvent != null)
            {
                Element click = currentEvent.getChild("click");
                Element prevClick =  prevEvent.getChild("click");
                Element prevPrevClick = prevPrevEvent.getChild("click");

                //Checking the last 3 events are clicks
                if(null != click && prevClick != null && prevPrevClick != null) {

                    String user = click.getChild("user").getText();
                    String prevUser =  prevClick.getChild("user").getText();
                    String prevPrevUser = prevPrevClick.getChild("user").getText();

                    //checking the last 3 events are from the same user
                    if(user.equals(prevUser) && prevUser.equals(prevPrevUser))
                    {
                        String app = click.getChild("application").getText();
                        String prevApp =  prevClick.getChild("application").getText();
                        String prevPrevApp = prevPrevClick.getChild("application").getText();

                        //checking if the last 3 clicks were in the same application
                        if(app.equals(prevApp) && prevApp.equals(prevPrevApp))
                        {
                            Point current = new Point();
                            Point prev = new Point();
                            Point prevPrev = new Point();

                            Point extremity1 = new Point();
                            Point extremity2 = new Point();

                            current.X = Double.parseDouble(click.getChild("x").getText());
                            current.Y = Double.parseDouble(click.getChild("y").getText());

                            prev.X = Double.parseDouble(prevClick.getChild("x").getText());
                            prev.Y = Double.parseDouble(prevClick.getChild("y").getText());

                            prevPrev.X = Double.parseDouble(prevPrevClick.getChild("x").getText());
                            prevPrev.Y = Double.parseDouble(prevPrevClick.getChild("y").getText());

                            if(current.X != prev.X)
                            {
                                //find a and b defining the line y = ax + b
                                double a = (current.Y - prev.Y) / (current.X - prev.X);
                                double b = current.Y - a * current.X;

                                if(prevPrev.X * a + b == prevPrev.Y)
                                {
                                    line = true;

                                    if(current.X > prev.X)
                                    {
                                        if(prev.X > prevPrev.X)
                                        {
                                            extremity1 = prevPrev;
                                            extremity2 = current;
                                        }
                                        else
                                        {
                                            extremity1 = prev;
                                            extremity2 = current.X > prevPrev.X ? current : prevPrev;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                if(current.X == prevPrev.X)
                                {
                                    line = true;

                                    if(current.Y > prev.Y)
                                    {
                                        if(prev.Y > prevPrev.Y)
                                        {
                                            extremity1 = prevPrev;
                                            extremity2 = current;
                                        }
                                        else
                                        {
                                            extremity1 = prev;
                                            extremity2 = current.Y > prevPrev.Y ? current : prevPrev;
                                        }
                                    }
                                }
                            }


                            if(line) {
                                //checking if all points are on the same straight line
                                System.out.format("    - Line with extremities [(%d,%d),(%d,%d)] in application %s  by user %s\n",
                                        (int) extremity1.X,
                                        (int) extremity1.Y,
                                        (int) extremity2.X,
                                        (int) extremity2.Y,
                                        app,
                                        user);
                            }

                        }

                    }
                }
            }

            prevPrevEvent = prevEvent;
            prevEvent = currentEvent;
        }

    }
}
