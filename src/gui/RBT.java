package gui;


public class RBT {
    private node root;
    int i = 0;

    public RBT() 
    {
        root = null;
    }

    private int height(node newNode) {
        if (newNode == null)
            return 0;
        return 1 + Math.max(height(newNode.left), height(newNode.right));
    }

    private int balanceFactor(node newNode) {
        return height(newNode.left) - height(newNode.right);
    }


    private node leftLeft(node newNode) {
        node temp;
        temp = newNode.left;
        temp.parent = newNode.parent;
        newNode.left = temp.right;
        if (newNode.left != null)
            newNode.left.parent = newNode;
        temp.right = newNode;
        newNode.parent = temp;
        return temp;
    }

    private node leftRight(node newNode) {
        node temp;
        temp = newNode.left.right;
        newNode.left.right = temp.left;
        if (temp.left != null)
            temp.left.parent = newNode.left;
        temp.left = newNode.left;
        if (temp.left != null)
            temp.left.parent = temp;
        newNode.left = temp;
        temp.parent = newNode;
        return newNode;
    }

    private node rightRight(node newNode) {
        node temp;
        temp = newNode.right;
        temp.parent = newNode.parent;
        newNode.right = temp.left;
        if (temp.left != null)
            temp.left.parent = newNode;
        temp.left = newNode;
        newNode.parent = temp;
        return temp;
    }

    private node rightLeft(node newNode) {
        node temp;
        temp = newNode.right.left;
        temp.parent = newNode;
        newNode.right.left = temp.right;
        if (temp.right != null)
            temp.right.parent = newNode.right;
        temp.right = newNode.right;
        newNode.right.parent = temp;
        newNode.right = temp;
        return newNode;
    }

    private void checkDR(node newNode) {
        if (newNode.parent.color && newNode.color)
            fixDR(newNode);
    }

    private void LLfixDR(node grand, node p) {
        grand.color = !grand.color;
        p.color = !p.color;
        print2D();
        if (grand == root) {
            root = grand.left;
            grand = leftLeft(grand);
            return;
        }
        if(grand.parent.left == grand)
            grand.parent.left = leftLeft(grand);
        else
            grand.parent.right = leftLeft(grand);
        /*if (balanceFactor(grand.parent) > 0)
            grand.parent.left = leftLeft(grand);
        else
            grand.parent.right = leftLeft(grand);*/
        // print2D();
    }

    private void LRfixDR(node grand) {
        grand = leftRight(grand);
        //print2D();
        LLfixDR(grand, grand.left);
    }

    private void RRfixDR(node grand, node p) {
        // print2D();
        grand.color = !grand.color;
        p.color = !p.color;
        //print2D();
        if (grand == root) {
            root = grand.right;
            grand = rightRight(grand);
            return;
        }
        if (grand.parent.left == grand)
            grand.parent.left = rightRight(grand);
        else
            grand.parent.right = rightRight(grand);
        /*if (balanceFactor(grand.parent) > 0)
            grand.parent.left = rightRight(grand);
        else
            grand.parent.right = rightRight(grand);*/
    }

    private void RLfixDR(node grand) {
        grand = rightLeft(grand);
        RRfixDR(grand, grand.right);
    }

    private void fixDR(node newNode) {
        node child = newNode;
        node p = newNode.parent;
        node grand = p.parent;
        node uncle;
        if (grand.left == p)
            uncle = grand.right;
        else
            uncle = grand.left;
        if (uncle == null || uncle.color == false) {
            boolean l1, l2, r1, r2;
            if (balanceFactor(grand) > 0) {
                l1 = true;
                r1 = false;
            } else {
                l1 = false;
                r1 = true;
            }
            if (balanceFactor(p) > 0) {
                l2 = true;
                r2 = false;
            } else {
                l2 = false;
                r2 = true;
            }
            if (l1 && l2)
                LLfixDR(grand, p);
            else if (l1 && r2) {
                LRfixDR(grand);
                //print2D();
            } else if (r1 && r2)
                RRfixDR(grand, p);
            else if (r1 && l2)
                RLfixDR(grand);
        } else {
            uncle.color = !uncle.color;
            p.color = !p.color;
            if (grand != root)
                grand.color = !grand.color;
        }
    }

    private void insert(node newNode, node temp, node p) {
        if (temp == null) {
            newNode.parent = p;
            //temp = newNode;
            if (p.info > newNode.info) {
                p.left = newNode;
            } else {
                p.right = newNode;
            }
        } else if (temp.info > newNode.info) {
            insert(newNode, temp.left, temp);
            checkDR(newNode);
        } else {
            insert(newNode, temp.right, temp);
            checkDR(newNode);
            if (temp != null && temp.parent != null)
                checkDR(temp);
        }
        // System.out.println("");
        // System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + i++);
        // print2D();
    }

    private void print2DUtil(node newNode, int space) {
        if (newNode == null)
            return;
        space += 10;
        print2DUtil(newNode.right, space);
        System.out.println();
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        if (newNode.color)
            System.out.println('R');
        else
            System.out.println('B');
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.print(newNode.info);
        print2DUtil(newNode.left, space);
    }

    public void insertRBT(int ele) {
        node newNode = new node();
        newNode.info = ele;
        newNode.left = newNode.right = null;
        newNode.color = true;
        newNode.parent = null;
        if (root == null) {
            newNode.color = !newNode.color;
            root = newNode;
        } else {
            node n;
            n = null;
            insert(newNode, root, n);
        }
    }

    public void print2D() {
        print2DUtil(root, 0);
    }


    //**DELETION**\\
    private node getSibling(node p) {
        if (p.parent == null)
            return null;

        if (p == p.parent.left) {
            return p.parent.right;
        } else {
            return p.parent.left;
        }
    }

    private node successor(node p) {
        node temp = new node();
        temp = p;

        while (temp.left != null)
            temp = temp.left;
//      
        return temp;
    }


    private void swap(node rep, node p) {
        int temp = rep.info;
        rep.info = p.info;
        p.info = temp;
    }

    private node replacementRBT(node p) {
        if (p.left != null && p.right != null) {
            return successor(p.right);
        }


        if (p.left == null && p.right == null)
            return null;

        if (p.left != null)
            return p.left;
        else
            return p.right;
    }

    private void rotateLeft(node p) {
        node newP = new node();
        newP = p.right;

        if (p == root)
            root = newP;

        if (p.parent != null) {
            if (p == p.parent.left) {
                p.parent.left = newP;
            } else {
                p.parent.right = newP;
            }
        }

        newP.parent = p.parent;
        p.parent = newP;
        p.right = newP.left;

        if (newP.left != null)
            newP.left.parent = p;

        newP.left = p;
    }

    private void rotateRight(node p) {
        node newP = new node();
        newP = p.left;

        if (p == root)
            root = newP;

        if (p.parent != null) {
            if (p == p.parent.left) {
                p.parent.left = newP;
            } else {
                p.parent.right = newP;
            }
        }

        newP.parent = p.parent;
        p.parent = newP;
        p.left = newP.right;

        if (newP.right != null)
            newP.right.parent = p;

        newP.right = p;
    }

    private void fixDB(node p) {
        if (p == root)
            return;

        node sibling = new node();
        sibling = getSibling(p);
        if (sibling == null) {
            fixDB(p.parent);
        } else {
            if (sibling.color) {
                p.parent.color = true;
                sibling.color = false;
                if (sibling == p.left) {
                    rotateRight(p.parent);
                } else {
                    rotateLeft(p.parent);
                }
                fixDB(p);
            } else {
                if (sibling.left == null || sibling.right == null) {
                    sibling.color = true;
                    if (!p.parent.color)
                        fixDB(p.parent);
                    else
                        p.parent.color = false;
                } else if ((sibling.left.color && sibling.left != null) || (sibling.right.color && sibling.right != null)) {
                    //if sibling has red child
                    if (sibling.left != null && sibling.left.color) {
                        //the left child is red
                        if (sibling == p.left) {
                            sibling.left.color = sibling.color;
                            sibling.color = p.parent.color;
                            rotateRight(p.parent);
                        } else {
                            sibling.left.color = p.parent.color;
                            rotateRight(sibling);
                            rotateLeft(p.parent);
                        }
                    } else {
                        if (sibling == p.left) {
                            sibling.right.color = p.parent.color;
                            rotateLeft(sibling);
                            rotateRight(p.parent);
                        } else {
                            // right right
                            sibling.right.color = sibling.color;
                            sibling.color = p.parent.color;
                            rotateLeft(p.parent);
                        }
                    }
                    p.parent.color = false;
                } else {
                    sibling.color = true;
                    if (!p.parent.color)
                        fixDB(p.parent);
                    else
                        p.parent.color = false;
                }
            }
        }
    }

    private void deletion(node p) {
        node replacement = new node();
        replacement = replacementRBT(p);
        if (replacement == null) { // leaf 
            if (p == root)
                root = null;
            else {
                if ((replacement == null || !replacement.color) && (!p.color)) {
                    fixDB(p);
                } else {
                    if (getSibling(p) != null) 
                    {
                        getSibling(p).color = true;
                    }

                }
                if (p == p.parent.left) //if p is left of grand parent
                    p.parent.left = null;
                else
                    p.parent.right = null;
            }
            p = null;
            return;
        }

        if (p.left == null || p.right == null) {
            // node to be deleted has 1 child
            if (p == root) {
                p.info = replacement.info;
                p.left = p.right = null;
                replacement = null;
            } else {
                if (p == p.parent.left) {
                    p.parent.left = replacement;
                } else {
                    p.parent.right = replacement;
                }
                p = null;
                replacement.parent = p.parent;

                if ((replacement == null || !replacement.color) && (!p.color)) {
                    fixDB(replacement);
                } else {
                    replacement.color = false;
                }
            }
            return;
        }
        swap(replacement, p);
        deletion(replacement);
    }

    public void deletionRBT(int n) {
        if (root == null)
            return;

        node p = new node();
        p = search(n);

        if (p.info != n) {
            System.out.println("No node found : " + n);
            return;
        }
        deletion(p);
    }

    private node search(int p) {
        node newNode = new node();
        newNode = root;
        while (newNode != null) {
            if (p < newNode.info) {
                if (newNode.left == null)
                    break;
                else
                    newNode = newNode.left;
            } else if (p == newNode.info) {
                break;
            } else {
                if (newNode.right == null)
                    break;
                else
                    newNode = newNode.right;
            }
        }

        return newNode;
    }

	public node getRoot() {
		return root;
	}

	public int getDepth() {
		// TODO Auto-generated method stub
		return height(root);
	}
	public boolean isContain(int value){
        node temp = root;
        while(temp!= null){
            if(temp.info == value) return true;
            else if(temp.info>value) temp = temp.left;
            else temp = temp.right;
        }
        return false;
    }

	private void clear(node n) {
		if(n!=null)
		{
			deletionRBT(n.info);
			clear(n.left);
			clear(n.right);
			
		}
		
	}
	public void clearTree()
	{
		clear(root);
		root=null;
	}

}
