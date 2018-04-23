/***********************************************
* Authors: Micah Lund, Cris Cooper, Kyla Kunz
*
* Group Project - Music Player
************************************************/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class PlayerGUI extends JFrame {

   private int trackNumber = 0;
   private boolean isPlaying = false;
   private boolean Pause = false;
   private long clipTime = 0;
   private boolean playPauseImage = true;

   JButton previousButton = new JButton();
   JButton playPauseButton = new JButton();
   JButton stopButton = new JButton();
   JButton nextButton = new JButton();
   JButton shuffleButton = new JButton();
   JPanel currentSongPanel = new JPanel();
   JLabel currentSong = new JLabel();

   public void GUIControlls() {
      PlayerGUI musicController = new PlayerGUI();
      musicController.setVisible(true);
      
      buttonActions();
      contentLayout(musicController);  
      controllerDefaults(musicController);   
   }
   
   private void buttonActions() {
      previousButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {
         
            if (isPlaying) {
               Player.clip.stop();
            }
            
            if (trackNumber == 0) {
               trackNumber = Player.getSongArrayLength()-1;
            } else {
               trackNumber -= 1;
            }
            
            Player.player(1, trackNumber);
            isPlaying = true;
            Pause = false;
            
            playPauseImage = false;
            playPause();
         }
      });
      
      
      playPauseButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event){
            if (!isPlaying) {
               playPauseImage = false;
               playPause();

               Player.player(1, trackNumber);
               isPlaying = true;
            }
            else {
               playPauseImage = true;
               playPause();
               
               if (!Pause) {            
                  clipTime = Player.clip.getMicrosecondPosition();
                  Player.clip.stop();
                  Pause = true;
                  
                  playPauseImage = true;
                  playPause();

               } else {            
                  Player.clip.setMicrosecondPosition(clipTime);            
                  Player.clip.start();
                  Pause = false;
                  
                  playPauseImage = false;
                  playPause();
               }
            }
         } 
      });
      
      stopButton.addActionListener(new ActionListener() {         
         public void actionPerformed(ActionEvent event) {
            if (isPlaying) {
               Player.clip.stop();
               Player.player(2, trackNumber);
               isPlaying = false;
               
               playPauseImage = true;
               playPause();
            }
         }
      });
            
      nextButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {            
            if (isPlaying) {
               Player.clip.stop();
            }
            
            if (trackNumber == Player.getSongArrayLength()-1) {
               trackNumber = 0;
            } else {
               trackNumber += 1;
            }
            
            Player.player(1, trackNumber);
            isPlaying = true;
            Pause = false;
            
            playPauseImage = false;
            playPause();
         }
      });
      
      shuffleButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {
            
            if (isPlaying) {
               Player.clip.stop();
            }
                       
            Random rand = new Random();
            int randNumber = rand.nextInt(Player.getSongArrayLength());
            trackNumber = randNumber;
            Player.player(1, trackNumber);
            isPlaying = true;
            Pause = false;
            
            playPauseImage = false;
            playPause();
         }
      });
   }
   
   private void contentLayout(PlayerGUI musicController) {
      JPanel content = new JPanel();
      
      previousButton.setIcon(new ImageIcon("previousButton.png"));
      previousButton.setBackground(Color.WHITE);
            
      stopButton.setIcon(new ImageIcon("stopButton.png"));
      stopButton.setBackground(Color.WHITE);
      
      nextButton.setIcon(new ImageIcon("nextButton.png"));
      nextButton.setBackground(Color.WHITE);
      
      shuffleButton.setIcon(new ImageIcon("shuffleButton.png"));
      shuffleButton.setBackground(Color.WHITE);
      validate();
            
      content.add(new JLabel("Previous"));
      content.add(new JLabel("Play/Pause"));
      content.add(new JLabel("Stop"));      
      content.add(new JLabel("Next"));
      content.add(new JLabel("Shuffle"));
      
      content.add(previousButton);
      playPause();
      content.add(playPauseButton);
      content.add(stopButton);      
      content.add(nextButton);
      content.add(shuffleButton);  
      
      content.setLayout(new GridLayout(2, 5, 2, 0)); 
      
      currentSongPanel.add(new JLabel("Currently Playing: "));
      currentSong.setText("Put the song that's playing here");
      currentSongPanel.add(currentSong);
      
      currentSongPanel.setLayout(new GridLayout(2, 2, 2, 2));
      
      musicController.setLayout(new BorderLayout());
      musicController.add(content, BorderLayout.SOUTH);
      musicController.add(currentSongPanel, BorderLayout.NORTH);
      musicController.pack();

   }
   
   private void controllerDefaults(PlayerGUI musicController) {
      musicController.setSize(400,175);      
      musicController.setTitle("Music Player");
      musicController.setLocationRelativeTo(null);
      musicController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   private void playPause() {
      if(playPauseImage) {
         playPauseButton.setIcon(new ImageIcon("playButton.png"));
         playPauseButton.setBackground(Color.WHITE);
      }
      else {
         playPauseButton.setIcon(new ImageIcon("pauseButton.png"));
         playPauseButton.setBackground(Color.WHITE);
      }
   }
}