import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
     
        // Abrir conexão HTTP e buscar os Top 250 filmes do IMDB-API

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();  
       
        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD-JamesWebbSpaceTelescope";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();  

        var http = new ClienteHttp();
        String json = http.buscaDados(url);  

       
        //exibir e manipuldar os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
         
        var geradora = new GeradoraDeFigurinhas();
        
        for(int i = 0; i < 3; i++){

            Conteudo conteudo = conteudos.get(i); 

           
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";    
           
           
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();

            
            System.out.println("\u001b[37m\u001b[42mNome do Filme:\u001b[m" + conteudo.getTitulo());
            System.out.println("Imagem do Filme: " + conteudo.getUrlImagem());
            System.out.println("Avaliação: "+"\u2B50".repeat(5));
            System.out.println();
            
        }
    }
}
