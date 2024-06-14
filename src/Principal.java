
import java.awt.EventQueue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LuizVagner
 */
public class Principal {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    CadastroVIEW telaInicial = new CadastroVIEW();
                    telaInicial.setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            
        });
    }
}
