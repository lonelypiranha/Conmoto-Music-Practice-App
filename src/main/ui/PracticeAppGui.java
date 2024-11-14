package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Song;
import model.SongLibrary;

public class PracticeAppGui implements ActionListener {
    JFrame frame;
    JOptionPane paneAddSong;
    JLabel labelOpening;
    ImageIcon logo;
    JButton buttonOpening;
    JPanel panelOpening;
    JPanel panelButton;
    JPanel panelLabel;
    JPanel addSongTextFields;
    JPanel allSongPanels;

    JLabel topSongList;
    JPanel panelForTitle;
    JPanel barSongList;
    JPanel songList;
    JButton addSongButton;
    JButton removeSongButton;
    JButton detailsButton;
    JButton filterInstrumentButton;
    JButton filterComposerButton;
    JButton loadButton;
    JButton saveButton;
    JButton returnToMainButton;
    JButton quitButton;

    SongLibrary songlibrary;
    SongLibrary filteredLibrary;

    public PracticeAppGui() {
        frame = new JFrame();
        panelOpening = new JPanel();
        panelButton = new JPanel();
        panelLabel = new JPanel();
        logo = new ImageIcon("src/main/ui/on-2.png");
        songlibrary = new SongLibrary();
        filteredLibrary = new SongLibrary();

        labelOpening = new JLabel();
        labelOpening.setIcon(logo);
        labelOpening.setText("Welcome To Conmoto Music Practice App!");
        labelOpening.setHorizontalTextPosition(JLabel.CENTER);
        labelOpening.setVerticalTextPosition(JLabel.BOTTOM);
        labelOpening.setForeground(Color.WHITE);
        labelOpening.setFont(new Font("Futura", Font.BOLD, 30));
        labelOpening.setIconTextGap(-50);
        labelOpening.setHorizontalAlignment(JLabel.CENTER);
        labelOpening.setVerticalAlignment(JLabel.TOP);

        buttonOpening = new JButton();
        buttonOpening.setText("Start App");
        buttonOpening.setSize(100, 100);
        buttonOpening.setHorizontalAlignment(JButton.CENTER);
        buttonOpening.setVerticalAlignment(JButton.CENTER);
        buttonOpening.addActionListener(this);
        buttonOpening.setFocusable(false);
        buttonOpening.setHorizontalTextPosition(JButton.CENTER);
        buttonOpening.setVerticalTextPosition(JButton.CENTER);
        buttonOpening.setFont(new Font("Futura", Font.PLAIN, 20));
        buttonOpening.setForeground(new Color(2, 5, 98));

        panelOpening.setBackground(new Color(0, 0, 0, 0));
        panelButton.setBackground(new Color(0, 0, 0, 0));
        panelLabel.setBackground(new Color(0, 0, 0, 0));
        panelButton.setLayout(new GridBagLayout());
        panelButton.add(buttonOpening);
        panelLabel.add(labelOpening);

        panelOpening.setLayout(new BoxLayout(panelOpening, BoxLayout.PAGE_AXIS));
        panelOpening.add(panelLabel);
        panelOpening.add(Box.createVerticalStrut(40));
        panelOpening.add(panelButton);

        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(panelOpening);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(2, 5, 98));
    }

    public void displaySongList() {
        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.setVisible(true);

        topSongList = new JLabel();
        topSongList.setText("Your Song Library");
        topSongList.setHorizontalTextPosition(JLabel.CENTER);
        topSongList.setVerticalTextPosition(JLabel.BOTTOM);
        topSongList.setForeground(Color.WHITE);
        topSongList.setFont(new Font("Futura", Font.BOLD, 30));
        topSongList.setIconTextGap(-50);
        topSongList.setHorizontalAlignment(JLabel.CENTER);
        topSongList.setVerticalAlignment(JLabel.TOP);

        panelForTitle = new JPanel();
        panelForTitle.setBackground(new Color(0, 0, 0, 0));
        panelForTitle.add(topSongList);

        addSongButton = new JButton();
        addSongButton.setText("Add a song");
        addSongButton.setSize(100, 100);
        addSongButton.setHorizontalAlignment(JButton.CENTER);
        addSongButton.setVerticalAlignment(JButton.CENTER);
        addSongButton.addActionListener(this);
        addSongButton.setFocusable(false);
        addSongButton.setHorizontalTextPosition(JButton.CENTER);
        addSongButton.setVerticalTextPosition(JButton.CENTER);
        addSongButton.setFont(new Font("Futura", Font.PLAIN, 20));
        addSongButton.setForeground(new Color(2, 5, 98));

        removeSongButton = new JButton();
        removeSongButton.setText("Remove a song");
        removeSongButton.setSize(100, 100);
        removeSongButton.setHorizontalAlignment(JButton.CENTER);
        removeSongButton.setVerticalAlignment(JButton.CENTER);
        removeSongButton.addActionListener(this);
        removeSongButton.setFocusable(false);
        removeSongButton.setFont(new Font("Futura", Font.PLAIN, 20));
        removeSongButton.setForeground(new Color(2, 5, 98));

        detailsButton = new JButton();
        detailsButton.setText("See song details");
        detailsButton.setSize(100, 100);
        detailsButton.setHorizontalAlignment(JButton.CENTER);
        detailsButton.setVerticalAlignment(JButton.CENTER);
        detailsButton.addActionListener(this);
        detailsButton.setFocusable(false);
        detailsButton.setFont(new Font("Futura", Font.PLAIN, 20));
        detailsButton.setForeground(new Color(2, 5, 98));

        filterInstrumentButton = new JButton();
        filterInstrumentButton.setText("Filter song list by instrument");
        filterInstrumentButton.setSize(100, 100);
        filterInstrumentButton.setHorizontalAlignment(JButton.CENTER);
        filterInstrumentButton.setVerticalAlignment(JButton.CENTER);
        filterInstrumentButton.addActionListener(this);
        filterInstrumentButton.setFocusable(false);
        filterInstrumentButton.setFont(new Font("Futura", Font.PLAIN, 20));
        filterInstrumentButton.setForeground(new Color(2, 5, 98));

        filterComposerButton = new JButton();
        filterComposerButton.setText("Filter song list by composer name");
        filterComposerButton.setSize(100, 100);
        filterComposerButton.setHorizontalAlignment(JButton.CENTER);
        filterComposerButton.setVerticalAlignment(JButton.CENTER);
        filterComposerButton.addActionListener(this);
        filterComposerButton.setFocusable(false);
        filterComposerButton.setFont(new Font("Futura", Font.PLAIN, 20));
        filterComposerButton.setForeground(new Color(2, 5, 98));

        loadButton = new JButton();
        loadButton.setText("Load file");
        loadButton.setSize(100, 100);
        loadButton.setHorizontalAlignment(JButton.CENTER);
        loadButton.setVerticalAlignment(JButton.CENTER);
        loadButton.addActionListener(this);
        loadButton.setFocusable(false);
        loadButton.setFont(new Font("Futura", Font.PLAIN, 20));
        loadButton.setForeground(new Color(2, 5, 98));

        saveButton = new JButton();
        saveButton.setText("Save file");
        saveButton.setSize(100, 100);
        saveButton.setHorizontalAlignment(JButton.CENTER);
        saveButton.setVerticalAlignment(JButton.CENTER);
        saveButton.addActionListener(this);
        saveButton.setFocusable(false);
        saveButton.setFont(new Font("Futura", Font.PLAIN, 20));
        saveButton.setForeground(new Color(2, 5, 98));

        quitButton = new JButton();
        quitButton.setText("Quit app");
        quitButton.setSize(100, 100);
        quitButton.setHorizontalAlignment(JButton.CENTER);
        quitButton.setVerticalAlignment(JButton.CENTER);
        quitButton.addActionListener(this);
        quitButton.setFocusable(false);
        quitButton.setFont(new Font("Futura", Font.PLAIN, 20));
        quitButton.setForeground(new Color(2, 5, 98));

        barSongList = new JPanel();
        barSongList.setLayout(new GridLayout(4, 3));
        barSongList.setBackground(new Color(2, 5, 98));
        barSongList.add(addSongButton);
        barSongList.add(removeSongButton);
        barSongList.add(detailsButton);
        barSongList.add(filterComposerButton);
        barSongList.add(filterInstrumentButton);
        barSongList.add(loadButton);
        barSongList.add(saveButton);
        barSongList.add(quitButton);

        songList = new JPanel();
        songList.setLayout(new GridLayout(songlibrary.getSongList().size(), 1));
        songList.setBackground(Color.WHITE);

        if (songlibrary.getSongList().isEmpty()) {
            JLabel emptiness = new JLabel();
            emptiness.setText("Your song library is empty. Add a song!");
            emptiness.setForeground(Color.BLUE);
            emptiness.setFont(new Font("Futura", Font.BOLD, 20));
            songList.add(emptiness);
        } else {
            for (int i = 0; i < songlibrary.getSongList().size(); i++) {
                JLabel songTitle = new JLabel();
                songTitle.setText((i + 1) + ". " + songlibrary.getSongList().get(i).getTitle());
                songTitle.setForeground(Color.BLUE);
                songTitle.setFont(new Font("Futura", Font.BOLD, 20));
                // songTitle.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));

                // JPanel songPanel = new JPanel();
                // songPanel.add(songTitle);
                songList.add(songTitle);
            }
        }

        allSongPanels = new JPanel();
        allSongPanels.setLayout(new BoxLayout(allSongPanels, BoxLayout.PAGE_AXIS));
        allSongPanels.setBackground(new Color(0, 0, 0, 0));
        allSongPanels.add(panelForTitle);
        allSongPanels.add(Box.createVerticalStrut(40));
        allSongPanels.add(barSongList);
        allSongPanels.add(Box.createVerticalStrut(40));
        allSongPanels.add(songList);

        frame.add(allSongPanels);

        // frameSongList.add(panelForTitle);
        // frameSongList.add(barSongList);
        // frameSongList.add(songList);

    }

    public void addSong() {
        paneAddSong = new JOptionPane();
        addSongTextFields = new JPanel();
        addSongTextFields.setLayout(new GridLayout(5, 1));
        JTextField title = new JTextField();
        JTextField composer = new JTextField();
        JTextField instrument = new JTextField();
        JTextField barCount = new JTextField();
        JTextField tempo = new JTextField();
        addSongTextFields.add(new JLabel("Title:"));
        addSongTextFields.add(title);
        addSongTextFields.add(new JLabel("Composer:"));
        addSongTextFields.add(composer);
        addSongTextFields.add(new JLabel("Instrument:"));
        addSongTextFields.add(instrument);
        addSongTextFields.add(new JLabel("Number of bars:"));
        addSongTextFields.add(barCount);
        addSongTextFields.add(new JLabel("Tempo:"));
        addSongTextFields.add(tempo);
        int result = JOptionPane.showConfirmDialog(paneAddSong, addSongTextFields,
                "Please enter song details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titleForSong = title.getText();
            String composerForSong = composer.getText();
            String instrumentForSong = instrument.getText();
            int barForSong = Integer.parseInt(barCount.getText());
            int tempoForSong = Integer.parseInt(tempo.getText());
            Song newSong = new Song(titleForSong, composerForSong, instrumentForSong, barForSong, tempoForSong);
            songlibrary.addSong(newSong);
            JOptionPane.showOptionDialog(null, "Song successfully added!", "Message", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);
            frame.setVisible(false);
            displaySongList();
        }
    }

    public void removeSong() {
        paneAddSong = new JOptionPane();
        addSongTextFields = new JPanel();
        addSongTextFields.setLayout(new GridLayout(1, 1));
        JTextField titleToRemove = new JTextField();
        addSongTextFields.add(new JLabel("Enter the title of the song you'd like to remove:"));
        addSongTextFields.add(titleToRemove);
        int result = JOptionPane.showConfirmDialog(paneAddSong, addSongTextFields,
                "Remove song", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            boolean checker = false;
            for (Song s : songlibrary.getSongList()) {
                if (titleToRemove.getText().equals(s.getTitle())) {
                    checker = songlibrary.removeSong(s);
                    break;
                }
            }
            if (checker) {
                JOptionPane.showOptionDialog(null, "Song successfully removed!", "Message", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, null, null);
            } else {
                JOptionPane.showOptionDialog(null, "No song with that title was found!", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            }
            frame.setVisible(false);
            displaySongList();
        }
    }

    public void filterComposer() {
        paneAddSong = new JOptionPane();
        addSongTextFields = new JPanel();
        addSongTextFields.setLayout(new GridLayout(1, 1));
        JTextField composerToFilter = new JTextField();
        addSongTextFields.add(new JLabel("Enter the name of the composer you want to filter by:"));
        addSongTextFields.add(composerToFilter);
        int result = JOptionPane.showConfirmDialog(paneAddSong, addSongTextFields,
                "Filter by composer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            List<Song> filteredList = songlibrary.filterByComposer(composerToFilter.getText());
            if (filteredList.isEmpty()) {
                JOptionPane.showOptionDialog(null, "There are no songs with that composer", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            } else {
                filteredLibrary.setSongList(filteredList);
                frame.setVisible(false);
                displayFilteredLibraryMenu("composer", " \"" + composerToFilter.getText() + "\"");
            }
        }
    }

    public void filterInstrument() {
        paneAddSong = new JOptionPane();
        addSongTextFields = new JPanel();
        addSongTextFields.setLayout(new GridLayout(1, 1));
        JTextField instrumentToFilter = new JTextField();
        addSongTextFields.add(new JLabel("Enter the name of the instrument you want to filter by:"));
        addSongTextFields.add(instrumentToFilter);
        int result = JOptionPane.showConfirmDialog(paneAddSong, addSongTextFields,
                "Filter by instrument", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            List<Song> filteredList = songlibrary.filterByInstrument(instrumentToFilter.getText());
            if (filteredList.isEmpty()) {
                JOptionPane.showOptionDialog(null, "There are no songs with that instrument", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            } else {
                filteredLibrary.setSongList(filteredList);
                frame.setVisible(false);
                displayFilteredLibraryMenu("instrument", " \"" + instrumentToFilter.getText() + "\"");
            }
        }
    }

    public void displayFilteredLibraryMenu(String instrumentOrComposer, String name) {
        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.setVisible(true);

        topSongList = new JLabel();
        topSongList.setText("Your Song Library Filtered By The " + instrumentOrComposer + name);
        topSongList.setHorizontalTextPosition(JLabel.CENTER);
        topSongList.setVerticalTextPosition(JLabel.BOTTOM);
        topSongList.setForeground(Color.WHITE);
        topSongList.setFont(new Font("Futura", Font.BOLD, 30));
        topSongList.setIconTextGap(-50);
        topSongList.setHorizontalAlignment(JLabel.CENTER);
        topSongList.setVerticalAlignment(JLabel.TOP);

        panelForTitle = new JPanel();
        panelForTitle.setBackground(new Color(0, 0, 0, 0));
        panelForTitle.add(topSongList);

        // removeSongButton = new JButton();
        // removeSongButton.setText("Remove a song");
        // removeSongButton.setSize(100, 100);
        // removeSongButton.setHorizontalAlignment(JButton.CENTER);
        // removeSongButton.setVerticalAlignment(JButton.CENTER);
        // removeSongButton.addActionListener(this);
        // removeSongButton.setFocusable(false);
        // removeSongButton.setFont(new Font("Futura", Font.PLAIN, 20));
        // removeSongButton.setForeground(new Color(2, 5, 98));

        // detailsButton = new JButton();
        // detailsButton.setText("See song details");
        // detailsButton.setSize(100, 100);
        // detailsButton.setHorizontalAlignment(JButton.CENTER);
        // detailsButton.setVerticalAlignment(JButton.CENTER);
        // detailsButton.addActionListener(this);
        // detailsButton.setFocusable(false);
        // detailsButton.setFont(new Font("Futura", Font.PLAIN, 20));
        // detailsButton.setForeground(new Color(2, 5, 98));

        returnToMainButton = new JButton();
        returnToMainButton.setText("Return to full song library");
        returnToMainButton.setSize(100, 100);
        returnToMainButton.setHorizontalAlignment(JButton.CENTER);
        returnToMainButton.setVerticalAlignment(JButton.CENTER);
        returnToMainButton.addActionListener(this);
        returnToMainButton.setFocusable(false);
        returnToMainButton.setFont(new Font("Futura", Font.PLAIN, 20));
        returnToMainButton.setForeground(new Color(2, 5, 98));

        quitButton = new JButton();
        quitButton.setText("Quit app");
        quitButton.setSize(100, 100);
        quitButton.setHorizontalAlignment(JButton.CENTER);
        quitButton.setVerticalAlignment(JButton.CENTER);
        quitButton.addActionListener(this);
        quitButton.setFocusable(false);
        quitButton.setFont(new Font("Futura", Font.PLAIN, 20));
        quitButton.setForeground(new Color(2, 5, 98));

        barSongList = new JPanel();
        barSongList.setLayout(new GridLayout(1, 2));
        barSongList.setBackground(new Color(2, 5, 98));
        // barSongList.add(removeSongButton);
        // barSongList.add(detailsButton);
        barSongList.add(returnToMainButton);
        barSongList.add(quitButton);

        songList = new JPanel();
        songList.setLayout(new GridLayout(filteredLibrary.getSongList().size(), 1));
        songList.setBackground(Color.WHITE);

        for (int i = 0; i < filteredLibrary.getSongList().size(); i++) {
            JLabel songTitle = new JLabel();
            songTitle.setText((i + 1) + ". " + filteredLibrary.getSongList().get(i).getTitle());
            songTitle.setForeground(Color.BLUE);
            songTitle.setFont(new Font("Futura", Font.BOLD, 20));
            // songTitle.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));

            // JPanel songPanel = new JPanel();
            // songPanel.add(songTitle);
            songList.add(songTitle);
        }

        allSongPanels = new JPanel();
        allSongPanels.setLayout(new BoxLayout(allSongPanels, BoxLayout.PAGE_AXIS));
        allSongPanels.setBackground(new Color(0, 0, 0, 0));
        allSongPanels.add(panelForTitle);
        allSongPanels.add(Box.createVerticalStrut(40));
        allSongPanels.add(barSongList);
        allSongPanels.add(Box.createVerticalStrut(40));
        allSongPanels.add(songList);

        frame.add(allSongPanels);

        // frameSongList.add(panelForTitle);
        // frameSongList.add(barSongList);
        // frameSongList.add(songList);
    }

    public void displaySongDetails() {
        paneAddSong = new JOptionPane();
        addSongTextFields = new JPanel();
        addSongTextFields.setLayout(new GridLayout(1, 1));
        JTextField titleToRemove = new JTextField();
        addSongTextFields.add(new JLabel("Enter the title of the song you'd like to remove:"));
        addSongTextFields.add(titleToRemove);
        int result = JOptionPane.showConfirmDialog(paneAddSong, addSongTextFields,
                "Remove song", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            boolean checker = false;
            for (Song s : songlibrary.getSongList()) {
                if (titleToRemove.getText().equals(s.getTitle())) {
                    checker = songlibrary.removeSong(s);
                    break;
                }
            }
            if (checker) {
                JOptionPane.showOptionDialog(null, "Song successfully removed!", "Message", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, null, null);
            } else {
                JOptionPane.showOptionDialog(null, "No song with that title was found!", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            }
            frame.setVisible(false);
            displaySongList();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonOpening) {
            frame.setVisible(false);
            displaySongList();
        } else if (e.getSource() == addSongButton) {
            try {
                addSong();
            } catch (NumberFormatException nfe) {
                JOptionPane.showOptionDialog(null, "Number of bars and tempo must be an integer!", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            }
        } else if (e.getSource() == removeSongButton) {
            removeSong();
        } else if (e.getSource() == filterComposerButton) {
            filterComposer();
        } else if (e.getSource() == filterInstrumentButton) {
            filterInstrument();
        } else if (e.getSource() == returnToMainButton) {
            frame.setVisible(false);
            displaySongList();
        } else if (e.getSource() == detailsButton) {
            frame.setVisible(false);
            displaySongDetails();
        }
    }

}
