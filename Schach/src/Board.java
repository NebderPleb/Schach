package sus;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PipedWriter;
import java.util.LinkedList;
import javax.swing.JPanel;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
public class Board {
    public static LinkedList<sus.Peace> ps=new LinkedList<>();
    public static sus.Peace selectedPeace=null;
    public Board() throws IOException {

        BufferedImage all=ImageIO.read(new File("Bilder\\chess.png"));
        Image[] imgs =new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
          for(int x=0;x<1200;x+=200){
              imgs[ind]=all.getSubimage(x,y,200,200).getScaledInstance(64,64, BufferedImage.SCALE_SMOOTH);
              ind++;

          }
        }
       sus.Peace brook=new sus.Peace(0, 0, false, "rook", ps);
        sus.Peace bkinght=new sus.Peace(1, 0, false, "knight", ps);
       sus.Peace bbishop=new sus.Peace(2, 0, false, "bishop", ps);
        sus.Peace bqueen=new sus.Peace(3, 0, false, "queen", ps);
        sus.Peace bking=  new sus.Peace(4, 0, false, "king", ps);
        sus.Peace bbishop2=new sus.Peace(5, 0, false, "bishop", ps);
        sus.Peace bkight2=new sus.Peace(6, 0, false, "knight", ps);
        sus.Peace brook2=new sus.Peace(7, 0, false, "rook", ps);
        sus.Peace bpawn1=new sus.Peace(1, 1, false, "pawn", ps);
        sus.Peace bpawn2=new sus.Peace(2, 1, false, "pawn", ps);
        sus.Peace bpawn3=new sus.Peace(3, 1, false, "pawn", ps);
        sus.Peace bpawn4=new sus.Peace(4, 1, false, "pawn", ps);
        sus.Peace bpawn5=new sus.Peace(5, 1, false, "pawn", ps);
        sus.Peace bpawn6=new sus.Peace(6, 1, false, "pawn", ps);
        sus.Peace bpawn7=new sus.Peace(7, 1, false, "pawn", ps);
        sus.Peace bpawn8= new sus.Peace(0, 1, false, "pawn", ps);

        sus.Peace wrook=new sus.Peace(0, 7, true, "rook", ps);
        sus.Peace wkinght=new sus.Peace(1, 7, true, "knight", ps);
        sus.Peace wbishop=new sus.Peace(2, 7, true, "bishop", ps);
        sus.Peace wqueen=new sus.Peace(3, 7, true, "queen", ps);
        sus.Peace wking=new sus.Peace(4, 7, true, "king", ps);
        sus.Peace wbishop2=new sus.Peace(5, 7, true, "bishop", ps);
        sus.Peace wkight2=new sus.Peace(6, 7, true, "knight", ps);
        sus.Peace wrook2=new sus.Peace(7, 7, true, "rook", ps);
        sus.Peace wpawn1=new sus.Peace(1, 6, true, "pawn", ps);
        sus.Peace wpawn2=new sus.Peace(2, 6, true, "pawn", ps);
        sus.Peace wpawn3=new sus.Peace(3, 6, true, "pawn", ps);
        sus.Peace wpawn4=new sus.Peace(4, 6, true, "pawn", ps);
        sus.Peace wpawn5=new sus.Peace(5, 6, true, "pawn", ps);
        sus.Peace wpawn6=new sus.Peace(6, 6, true, "pawn", ps);
        sus.Peace wpawn7=new sus.Peace(7, 6, true, "pawn", ps);
        sus.Peace wpawn8=new sus.Peace(0, 6, true, "pawn", ps);


        JFrame frame = new JFrame();
        frame.setBounds(10,12,512,512);
        frame.setUndecorated(true);
        JPanel pn=new JPanel(){
            @Override
        public void paint(Graphics g){
            boolean white=true;
            for(int y=0;y<8;y++){
                for(int x= 0;x<8;x++){
                    if(white){
                        g.setColor(new Color(235,235, 208));
                    }else{
                        g.setColor(new Color(119, 148, 85));
                    }
                    g.fillRect(x*64,y*64,64,64);
                    white=!white;
                }
                white=!white;
            }
                    for(sus.Peace p:ps){
                        int ind=0;
                        if(p.name.equalsIgnoreCase("king")){
                            ind=0;
                        }
                        if(p.name.equalsIgnoreCase("Queen")){
                            ind=1;
                        }
                        if(p.name.equalsIgnoreCase("bishop")){
                            ind=2;
                        }
                        if(p.name.equalsIgnoreCase("knight")){
                            ind=3;
                        }
                        if(p.name.equalsIgnoreCase("rook")){
                            ind=4;
                        }
                        if(p.name.equalsIgnoreCase("pawn")){
                            ind=5;
                        }
                        if(!p.isWhite){
                            ind+=6;
                        }
                        g.drawImage(imgs[ind],p.x,p.y,this);

                    }


        }
        };
        frame.add(pn);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //  System.out.println(getPeace(e.getX(),e.getY()).isWhite?"white";"black")+getPeace(e.getX(),e.getY()).name);
                selectedPeace=getPeace(e.getX(),e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPeace.move(e.getX()/64,e.getY()/64);
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPeace!=null){

                    selectedPeace.x=e.getX()-32;
                    selectedPeace.y=e.getY()-32;
                    frame.repaint();
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
    public static sus.Peace getPeace(int x, int y){
        int xp=x/64;
        int yp=y/64;
        for(sus.Peace p: ps){
            if(p.xp==xp&&p.yp==yp){
                return p;
            }
        }
        return null;



    }

















}
