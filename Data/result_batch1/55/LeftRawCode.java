// https://github.com/RipMeApp/ripme/tree/531f235154024e17e367560ddaf0232401bd537b/src/main/java/com/rarchives/ripme/ui/MainWindow.java#L257-L646
public class TempClass {
    private void createUI(Container pane) {
        // If creating the tray icon fails, ignore it.
        try {
            setupTrayIcon();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

        EmptyBorder emptyBorder = new EmptyBorder(5, 5, 5, 5);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.ipadx = 2;
        gbc.gridx = 0;
        gbc.weighty = 0;
        gbc.ipady = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException
                | IllegalAccessException e) {
            LOGGER.error("[!] Exception setting system theme:", e);
        }

        ripTextfield = new JTextField("", 20);
        ripTextfield.addMouseListener(new ContextMenuMouseListener(ripTextfield));

        // Add keyboard protection of Ctrl+V for pasting.
        ripTextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 22) { // ASCII code for Ctrl+V
                    ContextActionProtections.pasteFromClipboard(ripTextfield);
                }
            }
        });

        /*
        Alternatively, just set this, and use
        ((AbstractDocument) ripTextfield.getDocument()).setDocumentFilter(new LengthLimitDocumentFilter(256));
            private static class LengthLimitDocumentFilter extends DocumentFilter {
                private final int maxLength;

                public LengthLimitDocumentFilter(int maxLength) {
                    this.maxLength = maxLength;
                }

                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        //            if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                        super.insertString(fb, offset, string.substring(0, maxLength), attr);
        //            }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    int currentLength = fb.getDocument().getLength();
                    int newLength = currentLength - length + text.length();

        //            if (newLength <= maxLength) {
                    super.replace(fb, offset, length, text.substring(0, maxLength), attrs);
        //            }
                }
            }
         */

        ImageIcon ripIcon = new ImageIcon(mainIcon);
        ripButton = new JButton("<html><font size=\"5\"><b>Rip</b></font></html>", ripIcon);
        stopButton = new JButton("<html><font size=\"5\"><b>Stop</b></font></html>");
        stopButton.setEnabled(false);
        try {
            Image stopIcon = ImageIO.read(getClass().getClassLoader().getResource("stop.png"));
            stopButton.setIcon(new ImageIcon(stopIcon));
        } catch (Exception ignored) {
        }
        JPanel ripPanel = new JPanel(new GridBagLayout());
        ripPanel.setBorder(emptyBorder);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.gridx = 0;
        ripPanel.add(new JLabel("URL:", JLabel.RIGHT), gbc);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        ripPanel.add(ripTextfield, gbc);
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridx = 2;
        ripPanel.add(ripButton, gbc);
        gbc.gridx = 3;
        ripPanel.add(stopButton, gbc);
        gbc.weightx = 1;

        statusLabel = new JLabel(Utils.getLocalizedString("inactive"));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        openButton = new JButton();
        openButton.setVisible(false);
        JPanel statusPanel = new JPanel(new GridBagLayout());
        statusPanel.setBorder(emptyBorder);

        gbc.gridx = 0;
        statusPanel.add(statusLabel, gbc);
        gbc.gridy = 1;
        statusPanel.add(openButton, gbc);
        gbc.gridy = 0;

        JPanel progressPanel = new JPanel(new GridBagLayout());
        progressPanel.setBorder(emptyBorder);
        statusProgress = new JProgressBar(0, 100);
        progressPanel.add(statusProgress, gbc);

        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBorder(emptyBorder);
        optionLog = new JButton(Utils.getLocalizedString("Log"));
        optionHistory = new JButton(Utils.getLocalizedString("History"));
        optionQueue = new JButton(Utils.getLocalizedString("queue"));
        optionConfiguration = new JButton(Utils.getLocalizedString("Configuration"));
        optionLog.setFont(optionLog.getFont().deriveFont(Font.PLAIN));
        optionHistory.setFont(optionLog.getFont().deriveFont(Font.PLAIN));
        optionQueue.setFont(optionLog.getFont().deriveFont(Font.PLAIN));
        optionConfiguration.setFont(optionLog.getFont().deriveFont(Font.PLAIN));
        try {
            Image icon;
            icon = ImageIO.read(getClass().getClassLoader().getResource("comment.png"));
            optionLog.setIcon(new ImageIcon(icon));
            icon = ImageIO.read(getClass().getClassLoader().getResource("time.png"));
            optionHistory.setIcon(new ImageIcon(icon));
            icon = ImageIO.read(getClass().getClassLoader().getResource("list.png"));
            optionQueue.setIcon(new ImageIcon(icon));
            icon = ImageIO.read(getClass().getClassLoader().getResource("gear.png"));
            optionConfiguration.setIcon(new ImageIcon(icon));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        gbc.gridx = 0;
        optionsPanel.add(optionLog, gbc);
        gbc.gridx = 1;
        optionsPanel.add(optionHistory, gbc);
        gbc.gridx = 2;
        optionsPanel.add(optionQueue, gbc);
        gbc.gridx = 3;
        optionsPanel.add(optionConfiguration, gbc);

        logPanel = new JPanel(new GridBagLayout());
        logPanel.setBorder(emptyBorder);
        logText = new JTextPane();
        logText.setEditable(false);
        JScrollPane logTextScroll = new JScrollPane(logText);
        logTextScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        logPanel.setVisible(false);
        logPanel.setPreferredSize(new Dimension(300, 250));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        logPanel.add(logTextScroll, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        historyPanel = new JPanel(new GridBagLayout());
        historyPanel.setBorder(emptyBorder);
        historyPanel.setVisible(false);
        historyPanel.setPreferredSize(new Dimension(300, 250));

        historyTableModel = new AbstractTableModel() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getColumnName(int col) {
                return HISTORY.getColumnName(col);
            }

            @Override
            public Class<?> getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            @Override
            public Object getValueAt(int row, int col) {
                return HISTORY.getValueAt(row, col);
            }

            @Override
            public int getRowCount() {
                return HISTORY.toList().size();
            }

            @Override
            public int getColumnCount() {
                return HISTORY.getColumnCount();
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return (col == 0 || col == 4);
            }

            @Override
            public void setValueAt(Object value, int row, int col) {
                if (col == 4) {
                    HISTORY.get(row).selected = (Boolean) value;
                    historyTableModel.fireTableDataChanged();
                }
            }
        };

        historyTable = new JTable(historyTableModel);
        historyTable.addMouseListener(new HistoryMenuMouseListener());
        historyTable.setAutoCreateRowSorter(true);

        for (int i = 0; i < historyTable.getColumnModel().getColumnCount(); i++) {
            int width = 130; // Default
            switch (i) {
            case 0: // URL
                width = 270;
                break;
            case 3:
                width = 40;
                break;
            case 4:
                width = 15;
                break;
            }
            historyTable.getColumnModel().getColumn(i).setPreferredWidth(width);
        }

        JScrollPane historyTableScrollPane = new JScrollPane(historyTable);
        historyButtonRemove = new JButton(Utils.getLocalizedString("remove"));
        historyButtonClear = new JButton(Utils.getLocalizedString("clear"));
        historyButtonRerip = new JButton(Utils.getLocalizedString("re-rip.checked"));
        gbc.gridx = 0;
        // History List Panel
        JPanel historyTablePanel = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        historyTablePanel.add(historyTableScrollPane, gbc);
        gbc.ipady = 180;
        gbc.gridy = 0;
        historyPanel.add(historyTablePanel, gbc);
        gbc.ipady = 0;
        JPanel historyButtonPanel = new JPanel(new GridBagLayout());
        historyButtonPanel.setSize(new Dimension(300, 10));
        historyButtonPanel.setBorder(emptyBorder);
        gbc.gridx = 0;
        historyButtonPanel.add(historyButtonRemove, gbc);
        gbc.gridx = 1;
        historyButtonPanel.add(historyButtonClear, gbc);
        gbc.gridx = 2;
        historyButtonPanel.add(historyButtonRerip, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        historyPanel.add(historyButtonPanel, gbc);

        queuePanel = new JPanel(new GridBagLayout());
        queuePanel.setBorder(emptyBorder);
        queuePanel.setVisible(false);
        queuePanel.setPreferredSize(new Dimension(300, 250));
        queueListModel = new DefaultListModel<>();
        JList<Object> queueList = new JList<>(queueListModel);
        queueList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        QueueMenuMouseListener queueMenuMouseListener = new QueueMenuMouseListener(d -> updateQueue(queueListModel));
        queueList.addMouseListener(queueMenuMouseListener);
        JScrollPane queueListScroll = new JScrollPane(queueList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        for (String item : Utils.getConfigList("queue")) {
            queueListModel.addElement(item);
        }
        updateQueue();

        gbc.gridx = 0;
        JPanel queueListPanel = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        queueListPanel.add(queueListScroll, gbc);
        queuePanel.add(queueListPanel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.ipady = 0;

        configurationPanel = new JPanel(new GridBagLayout());
        configurationPanel.setBorder(emptyBorder);
        configurationPanel.setVisible(false);

        // TODO Configuration components
        configUpdateButton = new JButton(Utils.getLocalizedString("check.for.updates"));
        configUpdateLabel = new JLabel(
                Utils.getLocalizedString("current.version") + ": " + UpdateUtils.getThisJarVersion(), JLabel.RIGHT);
        configThreadsLabel = new JLabel(Utils.getLocalizedString("max.download.threads"), JLabel.RIGHT);
        configTimeoutLabel = new JLabel(Utils.getLocalizedString("timeout.mill"), JLabel.RIGHT);
        configRetriesLabel = new JLabel(Utils.getLocalizedString("retry.download.count"), JLabel.RIGHT);
        configRetrySleepLabel = new JLabel(Utils.getLocalizedString("retry.sleep.mill"), JLabel.RIGHT);
        configThreadsText = configField("threads.size", 3);
        configTimeoutText = configField("download.timeout", 60000);
        configRetriesText = configField("download.retries", 3);
        configRetrySleepText = configField("download.retry.sleep", 5000);

        configOverwriteCheckbox = addNewCheckbox(Utils.getLocalizedString("overwrite.existing.files"), "file.overwrite",
                false);
        configAutoupdateCheckbox = addNewCheckbox(Utils.getLocalizedString("auto.update"), "auto.update", true);
        configPlaySound = addNewCheckbox(Utils.getLocalizedString("sound.when.rip.completes"), "play.sound", false);
        configShowPopup = addNewCheckbox(Utils.getLocalizedString("notification.when.rip.starts"),
                "download.show_popup", false);
        configSaveOrderCheckbox = addNewCheckbox(Utils.getLocalizedString("preserve.order"), "download.save_order",
                true);
        configSaveLogs = addNewCheckbox(Utils.getLocalizedString("save.logs"), "log.save", false);
        configSaveURLsOnly = addNewCheckbox(Utils.getLocalizedString("save.urls.only"), "urls_only.save", false);
        configSaveAlbumTitles = addNewCheckbox(Utils.getLocalizedString("save.album.titles"), "album_titles.save",
                true);
        configClipboardAutorip = addNewCheckbox(Utils.getLocalizedString("autorip.from.clipboard"), "clipboard.autorip",
                false);
        configSaveDescriptions = addNewCheckbox(Utils.getLocalizedString("save.descriptions"), "descriptions.save",
                true);
        configPreferMp4 = addNewCheckbox(Utils.getLocalizedString("prefer.mp4.over.gif"), "prefer.mp4", false);
        configWindowPosition = addNewCheckbox(Utils.getLocalizedString("restore.window.position"), "window.position",
                true);
        configURLHistoryCheckbox = addNewCheckbox(Utils.getLocalizedString("remember.url.history"),
                "remember.url_history", true);
        configSSLVerifyOff = addNewCheckbox(Utils.getLocalizedString("ssl.verify.off"),
                "ssl.verify.off", false);
        configUrlFileChooserButton = new JButton(Utils.getLocalizedString("download.url.list"));

        configLogLevelCombobox = new JComboBox<>(
                new String[] { "Log level: Error", "Log level: Warn", "Log level: Info", "Log level: Debug" });
        configSelectLangComboBox = new JComboBox<>(Utils.getSupportedLanguages());
        configSelectLangComboBox.setSelectedItem(Utils.getConfigString("lang", Utils.getSelectedLanguage()));
        configLogLevelCombobox.setSelectedItem(Utils.getConfigString("log.level", "Log level: Debug"));
        setLogLevel(configLogLevelCombobox.getSelectedItem().toString());
        configSaveDirLabel = new JLabel();
        try {
            String workingDir = (Utils.shortenPath(Utils.getWorkingDirectory()));
            configSaveDirLabel.setText(workingDir);
            configSaveDirLabel.setForeground(Color.BLUE);
            configSaveDirLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        configSaveDirLabel.setToolTipText(configSaveDirLabel.getText());
        configSaveDirLabel.setHorizontalAlignment(JLabel.RIGHT);
        configSaveDirButton = new JButton(Utils.getLocalizedString("select.save.dir") + "...");

        var idx = 0;
        addItemToConfigGridBagConstraints(gbc, idx++, configUpdateLabel, configUpdateButton);
        addItemToConfigGridBagConstraints(gbc, idx++, configAutoupdateCheckbox, configLogLevelCombobox);
        addItemToConfigGridBagConstraints(gbc, idx++, configThreadsLabel, configThreadsText);
        addItemToConfigGridBagConstraints(gbc, idx++, configTimeoutLabel, configTimeoutText);
        addItemToConfigGridBagConstraints(gbc, idx++, configRetriesLabel, configRetriesText);
        addItemToConfigGridBagConstraints(gbc, idx++, configRetrySleepLabel, configRetrySleepText);
        addItemToConfigGridBagConstraints(gbc, idx++, configOverwriteCheckbox, configSaveOrderCheckbox);
        addItemToConfigGridBagConstraints(gbc, idx++, configPlaySound, configSaveLogs);
        addItemToConfigGridBagConstraints(gbc, idx++, configShowPopup, configSaveURLsOnly);
        addItemToConfigGridBagConstraints(gbc, idx++, configClipboardAutorip, configSaveAlbumTitles);
        addItemToConfigGridBagConstraints(gbc, idx++, configSaveDescriptions, configPreferMp4);
        addItemToConfigGridBagConstraints(gbc, idx++, configWindowPosition, configURLHistoryCheckbox);
        addItemToConfigGridBagConstraints(gbc, idx++, configSSLVerifyOff, configSSLVerifyOff);
        addItemToConfigGridBagConstraints(gbc, idx++, configSelectLangComboBox, configUrlFileChooserButton);
        addItemToConfigGridBagConstraints(gbc, idx++, configSaveDirLabel, configSaveDirButton);

        emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(0, 0));
        emptyPanel.setSize(0, 0);

        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.gridy = 0;
        pane.add(ripPanel, gbc);
        gbc.gridy = 1;
        pane.add(statusPanel, gbc);
        gbc.gridy = 2;
        pane.add(progressPanel, gbc);
        gbc.gridy = 3;
        pane.add(optionsPanel, gbc);
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 4;
        pane.add(logPanel, gbc);
        gbc.gridy = 5;
        pane.add(historyPanel, gbc);
        gbc.gridy = 5;
        pane.add(queuePanel, gbc);
        gbc.gridy = 5;
        pane.add(configurationPanel, gbc);
        gbc.gridy = 5;
        pane.add(emptyPanel, gbc);
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

}