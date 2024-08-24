package dao;

import model.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoasDao {

    private File arquivo;

    public PessoasDao(){

        arquivo = new File("pessoas.ser");

        if(!arquivo.exists()){
            try{
                arquivo.createNewFile();
            }catch (IOException e){
                System.out.println("Falha ao criar arquivo");
            }
        }

    }

    public Set<Pessoa> getPessoas(){
        if(arquivo.length()>0){
            //há dados no arquivo, ler conjunto
            try {
                FileInputStream inputStream = new FileInputStream(arquivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Set<Pessoa> pessoas = (Set<Pessoa>) objectInputStream.readObject();
                return pessoas;
            }catch (FileNotFoundException e){
                System.out.println("Arquivo não encontrado");
            }catch (IOException e){
                System.out.println(e);
                System.out.println("Falha ao ler arquivo");
            }catch (ClassNotFoundException e){
                System.out.println("Falha ao ler arquivo");
            }
        }
        //não há nada no arquivo, criar um novo conjunto
        return new HashSet<>();
    }

    public boolean salvar(Pessoa pessoa){
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.add(pessoa)){
            try {
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            }catch (FileNotFoundException e){
                System.out.println("Arquivo não encontrado");
            }catch (IOException e){
                System.out.println("Falha ao escrever arquivo");
            }
        }
        return false;
    }

    public boolean deletarPorEmail(String email){
        Set<Pessoa> pessoas = getPessoas();
        for(Pessoa pessoa : pessoas) {
            if(pessoa.getEmail().equalsIgnoreCase(email)) {
                if (pessoas.remove(pessoa)) {
                    try {
                        FileOutputStream outputStream = new FileOutputStream(arquivo);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                        objectOutputStream.writeObject(pessoas);
                        return true;
                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo não encontrado");
                    } catch (IOException e) {
                        System.out.println("Falha ao escrever arquivo");
                    }
                }
            }
        }
        return false;
    }
}
