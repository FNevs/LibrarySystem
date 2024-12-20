package factory;



public class ItemBibliotecaFactory {
    public static ItemBiblioteca criaItem(String titulo, String autor, String tipo,String area){
        switch (tipo) {
            case "livro":
                return new Livro(titulo, autor, tipo, area);       
            case "periodico":
                return new Periodico(titulo, autor, tipo, area); 
            case "jornal":
                return new Jornal(titulo, autor, tipo, area);
            case "materialaudiovisual":
                return new MaterialAudioVisual(titulo, autor, tipo, area);
            default:
             throw new IllegalArgumentException("Tipo Invalido: " + tipo);
        }
    }
}