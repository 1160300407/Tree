package algo06;

import util.TreeNode;

public class Algo06Main {

    public static void main(String[] args) {
        TreeNode a = new TreeNode("a",5);
        TreeNode b = new TreeNode("b",1);
        TreeNode c = new TreeNode("c",1);
        TreeNode d = new TreeNode("d",2);
        TreeNode e = new TreeNode("e",2);
        TreeNode f = new TreeNode("f",1);
        a.addSon(b,c,f);
        c.addSon(d,e);

//        TreeNode a = new TreeNode("a",3);
//        TreeNode b = new TreeNode("b",2);
//        TreeNode c = new TreeNode("c",1);
//        TreeNode d = new TreeNode("d",2);
//        TreeNode e = new TreeNode("e",2);
//        TreeNode f = new TreeNode("f",1);
//        TreeNode g = new TreeNode("g",1);
//        TreeNode h = new TreeNode("h",2);
//        a.addSon(b,c,f,g,h);
//        c.addSon(d,e);
//
//        FDW fd = new FDW(a, 5);
//        fd.getPartition();
//        FDW result : ok


        //RS rs = new RS(a,5);
        //System.out.println(rs.getPartition());
        //RS result : ok

//        PUREDFS puredfs = new PUREDFS(a,5);
//        System.out.println(puredfs.getPartition());
//        puredfs result: ok

//        PUREBFS purebfs = new PUREBFS(a,5);
//        System.out.println(purebfs.getPartition());
//        purebfs result : ok

//        KM km = new KM(a,5);
//        System.out.println(km.getPartition());
//        km result : ok

//        EKM ekm = new EKM(a,5);
//        System.out.println(ekm.getPartition());
//        ekm result : ok

        //DHW dhw = new DHW(a,5);

//        GHDW ghdw = new GHDW(5, a);
//        ghdw.getPartition();
//        ghdw result : ok


    }

}
