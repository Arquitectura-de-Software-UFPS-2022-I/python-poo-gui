/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.ArchivoPython;
import ufps.arqui.python.poo.gui.models.Directorio;
import ufps.arqui.python.poo.gui.views.IPanelView;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.*;
import java.awt.*;

/**
 * Implementación del componente JTree, vista para visualizar los archivos,
 * directorio y subdirectorios del proyecto.
 *
 * @author
 * http://www.java2s.com/Tutorials/Java/Swing/JTree/Add_and_delete_JTree_node_with_button_event_in_Java.htm
 */
public class ArbolDinamico implements IPanelView {

    private JPanel panel;
    private IProyectoController controller;

    protected DefaultMutableTreeNode rootNode;
    protected DefaultTreeModel treeModel;
    protected JTree tree;
    private boolean load;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private String currentPath;

    public ArbolDinamico(IProyectoController controller) {
        this.controller = controller;
        this.currentPath = "";
        this.panel = new JPanel(new GridLayout(1, 0));
        this.load = false;

        this.rootNode = new DefaultMutableTreeNode("src");
        this.treeModel = new DefaultTreeModel(this.rootNode);

        this.tree = new JTree(this.treeModel);

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);

        tree.addTreeSelectionListener((TreeSelectionEvent e) -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }
            int i = 0;
            this.currentPath = this.getPath(node);

            if (!this.currentPath.contains(".py")) {
                if (this.load) {
                    this.actualizarDiagramas(this.currentPath);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tree);
        this.panel.add(scrollPane);
    }

    private String getPath(DefaultMutableTreeNode node) {
        int i = 0;
        String path = "";
        for (TreeNode tn : node.getPath()) {
            if (i++ > 0) {
                path += tn + "\\";
            }
        }

        if (!path.isEmpty()) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    private void actualizarDiagramas(String path) {
        try {
            System.out.println(path);
            this.controller.obtenerClasesDesde(path);
        } catch (Exceptions ex) {
            mostrarError(ex);
        }
    }

    /**
     * Remueve el nodo actualmente seleccionado
     */
    public void removeCurrentNode() {
        TreePath currentSelection = tree.getSelectionPath();
        if (currentSelection != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection
                            .getLastPathComponent());
            this.removeNode(currentNode);
        }
        
        // Either there was no selection, or the root was selected.
        toolkit.beep();
    }

    /**
     * Remueve un nodo del <code>JTree</code>
     * @param node Nodo a remover
     */
    private void removeNode(MutableTreeNode node) {
        MutableTreeNode parent = (MutableTreeNode) (node.getParent());
        if (parent != null) {
            treeModel.removeNodeFromParent(node);
        }
    }
    
    /**
     * Resetea el <code>JTree</code>, este metodo toma el nodo raiz y borra <br>
     * todos los nodos hijos que este tenga, en consecuencia queda solo el nodo "src"
     */
    private void resetTree(){
        int currentChildCount = this.rootNode.getChildCount();
        for(int i = 0; i < currentChildCount; i++){
            this.removeNode((DefaultMutableTreeNode)this.rootNode.getChildAt(0));
        }
    }

    /**
     * Añade un nodo hijo al nodo actualmente seleccionado
     *
     * @param child Nodo hijo que sera insertado
     */
    public DefaultMutableTreeNode addObject(Object child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        }

        return addObject(parentNode, child, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
            Object child) {
        return addObject(parent, child, false);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
            Object child, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

        if (parent == null) {
            parent = rootNode;
        }

        // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

        // Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Pobla el JTree dado el directorio de trabajo
     *
     * @param directorioTrabajo Directorio raiz del proyecto
     */
    public void populate(Directorio directorioTrabajo) {
        this.resetTree();
        this.load = true;
        for (ArchivoPython file : directorioTrabajo.getArchivos()) {
            this.addObject(this.rootNode, file.getArchivo().getName());
        }
        for (Directorio subdir : directorioTrabajo.getDirectorios()) {
            this.populate(subdir, this.rootNode);
        }

    }

    /**
     * Pobla el JTree dado el directorio de trabajo de forma recursiva
     *
     * @param directorio Directorio a ser insertado
     * @param parent Nodo padre sobre el cual sera insertado el directorio
     */
    private void populate(Directorio directorio, DefaultMutableTreeNode parent) {

        DefaultMutableTreeNode node = this.addObject(parent, directorio.getDirectorio().getName());
        for (ArchivoPython file : directorio.getArchivos()) {
            this.addObject(node, file.getArchivo().getName());
        }
        for (Directorio subdir : directorio.getDirectorios()) {
            this.populate(subdir, node);
        }

    }

    /**
     * Retorna el directorio actualmente seleccionado
     * @return String directorio actualmente seleccionado
     */
    public String getCurrentPath() {
        return currentPath;
    }
}
