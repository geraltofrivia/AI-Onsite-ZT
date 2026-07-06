// https://github.com/gephi/gephi/tree/dbc674485e70d7fbd74ef8f8489c1b7ba3dab50f/modules/DesktopImport/src/main/java/org/gephi/desktop/importer/ReportPanel.java#L602-L877
public class TempClass {
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        processorStrategyRadio = new javax.swing.ButtonGroup();
        labelSrc = new javax.swing.JLabel();
        sourceLabel = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        tab1ScrollPane = new javax.swing.JScrollPane();
        issuesOutline = new org.netbeans.swing.outline.Outline();
        tab2ScrollPane = new javax.swing.JScrollPane();
        reportEditor = new javax.swing.JEditorPane();
        labelGraphType = new javax.swing.JLabel();
        processorPanel = new javax.swing.JPanel();
        statsPanel = new javax.swing.JPanel();
        labelNodeCount = new javax.swing.JLabel();
        labelEdgeCount = new javax.swing.JLabel();
        nodeCountLabel = new javax.swing.JLabel();
        edgeCountLabel = new javax.swing.JLabel();
        dynamicLabel = new javax.swing.JLabel();
        labelDynamic = new javax.swing.JLabel();
        labelMultiGraph = new javax.swing.JLabel();
        multigraphLabel = new javax.swing.JLabel();
        labelDynamicAtts = new javax.swing.JLabel();
        dynamicAttsLabel = new javax.swing.JLabel();
        labelGraphCount = new javax.swing.JLabel();
        graphCountLabel = new javax.swing.JLabel();
        moreOptionsPanel = new javax.swing.JPanel();
        moreOptionsLeftPanel = new javax.swing.JPanel();
        autoscaleCheckbox = new javax.swing.JCheckBox();
        createMissingNodesCheckbox = new javax.swing.JCheckBox();
        selfLoopCheckBox = new javax.swing.JCheckBox();
        labelParallelEdgesMergeStrategy = new javax.swing.JLabel();
        edgesMergeStrategyCombo = new javax.swing.JComboBox();
        graphTypeCombo = new javax.swing.JComboBox();
        moreOptionsLink = new org.jdesktop.swingx.JXHyperlink();

        labelSrc.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelSrc.text")); // NOI18N

        sourceLabel.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.sourceLabel.text")); // NOI18N

        tab1ScrollPane.setViewportView(issuesOutline);

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.tab1ScrollPane.TabConstraints.tabTitle"), tab1ScrollPane); // NOI18N

        reportEditor.setEditable(false);
        reportEditor.setFocusable(false);
        tab2ScrollPane.setViewportView(reportEditor);

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.tab2ScrollPane.TabConstraints.tabTitle"), tab2ScrollPane); // NOI18N

        labelGraphType.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelGraphType.text")); // NOI18N

        processorPanel.setLayout(new java.awt.GridBagLayout());

        statsPanel.setLayout(new java.awt.GridBagLayout());

        labelNodeCount.setFont(labelNodeCount.getFont().deriveFont(labelNodeCount.getFont().getStyle() | java.awt.Font.BOLD));
        labelNodeCount.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelNodeCount.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 6, 0);
        statsPanel.add(labelNodeCount, gridBagConstraints);

        labelEdgeCount.setFont(labelEdgeCount.getFont().deriveFont(labelEdgeCount.getFont().getStyle() | java.awt.Font.BOLD));
        labelEdgeCount.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelEdgeCount.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 10, 0);
        statsPanel.add(labelEdgeCount, gridBagConstraints);

        nodeCountLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nodeCountLabel.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.nodeCountLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 6, 0);
        statsPanel.add(nodeCountLabel, gridBagConstraints);

        edgeCountLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        edgeCountLabel.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.edgeCountLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 0);
        statsPanel.add(edgeCountLabel, gridBagConstraints);

        dynamicLabel.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.dynamicLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 6, 0);
        statsPanel.add(dynamicLabel, gridBagConstraints);

        labelDynamic.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelDynamic.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 6, 0);
        statsPanel.add(labelDynamic, gridBagConstraints);

        labelMultiGraph.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelMultiGraph.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 6, 0);
        statsPanel.add(labelMultiGraph, gridBagConstraints);

        multigraphLabel.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.multigraphLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 6, 0);
        statsPanel.add(multigraphLabel, gridBagConstraints);

        labelDynamicAtts.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelDynamicAtts.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 6, 0);
        statsPanel.add(labelDynamicAtts, gridBagConstraints);

        dynamicAttsLabel.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.dynamicAttsLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 6, 0);
        statsPanel.add(dynamicAttsLabel, gridBagConstraints);

        labelGraphCount.setFont(labelGraphCount.getFont().deriveFont(labelGraphCount.getFont().getStyle() | java.awt.Font.BOLD));
        labelGraphCount.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelGraphCount.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 6, 0);
        statsPanel.add(labelGraphCount, gridBagConstraints);

        graphCountLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        graphCountLabel.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.graphCountLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 6, 0);
        statsPanel.add(graphCountLabel, gridBagConstraints);

        moreOptionsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        moreOptionsPanel.setLayout(new java.awt.GridBagLayout());

        moreOptionsLeftPanel.setLayout(new java.awt.GridBagLayout());

        autoscaleCheckbox.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.autoscaleCheckbox.text")); // NOI18N
        autoscaleCheckbox.setToolTipText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.autoscaleCheckbox.toolTipText")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        moreOptionsLeftPanel.add(autoscaleCheckbox, gridBagConstraints);

        createMissingNodesCheckbox.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.createMissingNodesCheckbox.text")); // NOI18N
        createMissingNodesCheckbox.setToolTipText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.createMissingNodesCheckbox.toolTipText")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        moreOptionsLeftPanel.add(createMissingNodesCheckbox, gridBagConstraints);

        selfLoopCheckBox.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.selfLoopCheckBox.text")); // NOI18N
        selfLoopCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.selfLoopCheckBox.toolTipText")); // NOI18N
        selfLoopCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        moreOptionsLeftPanel.add(selfLoopCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        moreOptionsPanel.add(moreOptionsLeftPanel, gridBagConstraints);

        labelParallelEdgesMergeStrategy.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.labelParallelEdgesMergeStrategy.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        moreOptionsPanel.add(labelParallelEdgesMergeStrategy, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        moreOptionsPanel.add(edgesMergeStrategyCombo, gridBagConstraints);

        moreOptionsLink.setText(org.openide.util.NbBundle.getMessage(ReportPanel.class, "ReportPanel.moreOptionsLink.text")); // NOI18N
        moreOptionsLink.setFocusPainted(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(moreOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSrc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sourceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(processorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelGraphType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(graphTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moreOptionsLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSrc)
                    .addComponent(sourceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGraphType)
                    .addComponent(graphTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moreOptionsLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moreOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(processorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

}