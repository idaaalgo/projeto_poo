package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

//Tornar Editavel, Palestra, Participante e Local
public class Sistema {

    private ArrayList<Participante> participantes;
    private ArrayList<Palestrante> palestrantes;
    private ArrayList<Palestra> palestras;
    private ArrayList<Local> locais;
    private ArrayList<Apresentacao> apresentacoes;
    private ArrayList<Inscricao> inscricoes;
    private ArrayList<Alocacao> alocacoes;

    public Sistema() {
        this.participantes = new ArrayList<>();
        this.palestrantes = new ArrayList<>();
        this.palestras = new ArrayList<>();
        this.locais = new ArrayList<>();
        this.apresentacoes = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
        this.alocacoes = new ArrayList<>();
    }

    public ArrayList<Inscricao> getInscricaos() {
        return inscricoes;
    }

    public void setInscricaos(ArrayList<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public ArrayList<Alocacao> getAlocacoes() {
        return alocacoes;
    }

    public void setAlocacoes(ArrayList<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

    public ArrayList<Palestrante> getPalestrantes() {
        return palestrantes;
    }

    public void setPalestrantes(ArrayList<Palestrante> palestrantes) {
        this.palestrantes = palestrantes;
    }

    public ArrayList<Palestra> getPalestras() {
        return palestras;
    }

    public void setPalestras(ArrayList<Palestra> palestras) {
        this.palestras = palestras;
    }

    public ArrayList<Local> getLocais() {
        return locais;
    }

    public void setLocais(ArrayList<Local> locais) {
        this.locais = locais;
    }

    public ArrayList<Apresentacao> getPalestrante_palestras() {
        return apresentacoes;
    }

    public void setPalestrante_palestras(ArrayList<Apresentacao> apresentacoes) {
        this.apresentacoes = apresentacoes;
    }

    public ArrayList<Inscricao> getParticipante_palestras() {
        return inscricoes;
    }

    public void setParticipante_palestras(ArrayList<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public void AdicionarParticipante(Scanner sc) throws Exception {
        System.out.print("Insira o E-mail: ");
        String email = sc.nextLine().trim();
        if (participantes.stream().noneMatch(a -> a.getEmail().equalsIgnoreCase(email))) {
            System.out.print("Insira o Nome: ");
            String nome = sc.nextLine().trim();
    
            System.out.print("Insira o seu Endereço: ");
            String endereco = sc.nextLine().trim();
    
            ArrayList<String> telefones = new ArrayList<>();
            while (true) {
                System.out.print("Insira o " + (telefones.size() + 1) + "º Telefone: ");
                String telefone = sc.nextLine().trim();
                telefones.add(telefone);
    
                int stop;
                while (true) {
                    System.out.print("Gostaria de inserir mais um Telefone (1 - Sim, 2 - Não): ");
                    String input = sc.nextLine().trim();
                    try {
                        stop = Integer.parseInt(input);
                        if (stop == 1 || stop == 2) {
                            break;
                        } else {
                            System.out.println("Valor inválido, insira novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido, insira um número.");
                    }
                }
                if (stop == 2) {
                    break;
                }
            }
    
            Participante participante = new Participante(nome, endereco, email, participantes.size());
            participante.setTelefone(telefones);
    
            participantes.add(participante);
            System.out.println("\nAdição feita com sucesso\n");
        } else {
            throw new Exception("Email já utilizado");
        }
    }

    public void RemoverParticipante(Scanner sc) throws Exception {
        if (!participantes.isEmpty()) {
            System.out.println("Participantes: ");
            for (Participante participante : participantes) {
                System.out.println("\nNúmero: " + participantes.indexOf(participante));
                System.out.println("Código do Participante: " + participante.getCodigo());
                System.out.println("Nome: " + participante.getNome());
                System.out.println("Email: " + participante.getEmail());
                System.out.println("Endereço: " + participante.getEndereco() + "\n");
            }
            
            while (true) {
                System.out.print("Insira o Número do participante a ser removido ou -1 para cancelar operação: ");
                String input = sc.nextLine().trim();
                try {
                    int indexParticipante = Integer.parseInt(input);
                    if (indexParticipante >= 0 && indexParticipante < participantes.size()) {
                        Participante participante = participantes.get(indexParticipante);
                        inscricoes.removeIf(inscricao -> inscricao.getParticipante().equals(participante));
                        participantes.remove(participante);
                        System.out.println("\nRemoção feita com sucesso\n");
                        break;
                    } else if (indexParticipante == -1) {
                        System.out.println("Operação cancelada.");
                        break;
                    } else {
                        System.out.println("Valor inválido, insira um número válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, insira um número.");
                }
            }
        } else {
            throw new Exception("Nenhum participante cadastrado");
        }
    }    

    public void EditarParticipante(Scanner sc) throws Exception {
        if (!participantes.isEmpty()) {
            System.out.println("Participantes: ");
            for (Participante participante : participantes) {
                System.out.println("\nNúmero: " + participantes.indexOf(participante));
                System.out.println("Código do Participante: " + participante.getCodigo());
                System.out.println("Nome: " + participante.getNome());
                System.out.println("Email: " + participante.getEmail());
                System.out.println("Endereço: " + participante.getEndereco() + "\n");
            }
            
            while (true) {
                System.out.print("Insira o Número do participante a ser editado ou -1 para cancelar operação: ");
                String input = sc.nextLine().trim();
                try {
                    int indexParticipante = Integer.parseInt(input);
                    if (indexParticipante >= 0 && indexParticipante < participantes.size()) {
                        Participante participante = participantes.get(indexParticipante);
    
                        System.out.print("Insira o E-mail: ");
                        String email = sc.nextLine().trim();
                        boolean emailDisponivel = participantes.stream()
                                .noneMatch(a -> a.getEmail().equalsIgnoreCase(email)) || 
                                participante.getEmail().equalsIgnoreCase(email);
    
                        if (emailDisponivel) {
                            System.out.print("Insira o Nome: ");
                            String nome = sc.nextLine().trim();
    
                            System.out.print("Insira o seu Endereço: ");
                            String endereco = sc.nextLine().trim();
    
                            ArrayList<String> telefones = new ArrayList<>();
                            while (true) {
                                System.out.print("Insira o " + (telefones.size() + 1) + "º Telefone: ");
                                String telefone = sc.nextLine().trim();
                                telefones.add(telefone);
    
                                int stop;
                                while (true) {
                                    System.out.print("Gostaria de inserir mais um Telefone (1 - Sim, 2 - Não): ");
                                    String stopInput = sc.nextLine().trim();
                                    try {
                                        stop = Integer.parseInt(stopInput);
                                        if (stop == 1 || stop == 2) {
                                            break;
                                        } else {
                                            System.out.println("Valor inválido, insira novamente.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Entrada inválida, insira um número.");
                                    }
                                }
                                if (stop == 2) {
                                    break;
                                }
                            }
    
                            participante.setEmail(email);
                            participante.setNome(nome);
                            participante.setEndereco(endereco);
                            participante.setTelefone(telefones);
    
                            System.out.println("\nEdição feita com sucesso\n");
                            break;
                        } else {
                            throw new Exception("Email já utilizado");
                        }
                    } else if (indexParticipante == -1) {
                        System.out.println("Operação cancelada.");
                        break;
                    } else {
                        System.out.println("Valor inválido, insira um número válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, insira um número.");
                }
            }
        } else {
            throw new Exception("Nenhum participante cadastrado");
        }
    }    

    public void AdicionarPalestrante(Scanner sc) throws Exception {
        System.out.print("Insira o E-mail: ");
        String email = sc.nextLine().trim();
        if (palestrantes.stream().noneMatch(a -> a.getEmail().equalsIgnoreCase(email))) {
            System.out.print("Insira o Nome: ");
            String nome = sc.nextLine().trim();
    
            System.out.print("Insira o seu Endereço: ");
            String endereco = sc.nextLine().trim();
    
            System.out.print("Insira a especialidade do palestrante: ");
            String especialidade = sc.nextLine().trim();
    
            ArrayList<String> telefones = new ArrayList<>();
            while (true) {
                System.out.print("Insira o " + (telefones.size() + 1) + "º Telefone: ");
                String telefone = sc.nextLine().trim();
                telefones.add(telefone);
    
                int stop;
                while (true) {
                    System.out.print("Gostaria de inserir mais um Telefone (1 - Sim, 2 - Não): ");
                    String input = sc.nextLine().trim();
                    try {
                        stop = Integer.parseInt(input);
                        if (stop == 1 || stop == 2) {
                            break;
                        } else {
                            System.out.println("Valor inválido, insira novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida, insira um número.");
                    }
                }
                if (stop == 2) {
                    break;
                }
            }
    
            Palestrante palestrante = new Palestrante(nome, endereco, email, especialidade);
            palestrante.setTelefone(telefones);
            palestrantes.add(palestrante);
            System.out.println("\nAdição feita com sucesso\n");
        } else {
            throw new Exception("Email já utilizado");
        }
    }    

    public void RemoverPalestrante(Scanner sc) throws Exception {
        if (!palestrantes.isEmpty()) {
            System.out.println("Palestrantes: ");
            for (Palestrante palestrante : palestrantes) {
                System.out.println("\nNúmero: " + palestrantes.indexOf(palestrante));
                System.out.println("Nome: " + palestrante.getNome());
                System.out.println("Email: " + palestrante.getEmail());
                System.out.println("Endereço: " + palestrante.getEndereco());
                System.out.println("Especialidade: " + palestrante.getEspecialidade() + "\n");
            }
    
            while (true) {
                System.out.print("Insira o Número do palestrante a ser removido ou -1 para cancelar operação: ");
                String input = sc.nextLine().trim();
                try {
                    int indexPalestrante = Integer.parseInt(input);
                    if (indexPalestrante >= 0 && indexPalestrante < palestrantes.size()) {
                        Palestrante palestrante = palestrantes.get(indexPalestrante);
                        apresentacoes.removeIf(a -> a.getPalestrante().equals(palestrante));
                        palestrantes.remove(palestrante);
                        System.out.println("\nRemoção feita com sucesso\n");
                        break;
                    } else if (indexPalestrante == -1) {
                        System.out.println("Operação cancelada.");
                        break;
                    } else {
                        System.out.println("Valor inválido, insira um número válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, insira um número.");
                }
            }
        } else {
            throw new Exception("Nenhum palestrante cadastrado");
        }
    }    

    public void EditarPalestrante(Scanner sc) throws Exception {
        if (!palestrantes.isEmpty()) {
            System.out.println("Palestrantes: ");
            for (Palestrante palestrante : palestrantes) {
                System.out.println("\nNúmero: " + palestrantes.indexOf(palestrante));
                System.out.println("Nome: " + palestrante.getNome());
                System.out.println("Email: " + palestrante.getEmail());
                System.out.println("Endereço: " + palestrante.getEndereco());
                System.out.println("Especialidade: " + palestrante.getEspecialidade() + "\n");
            }
    
            while (true) {
                System.out.print("Insira o Número do palestrante a ser editado ou -1 para cancelar operação: ");
                String input = sc.nextLine().trim();
                try {
                    int indexPalestrante = Integer.parseInt(input);
                    if (indexPalestrante >= 0 && indexPalestrante < palestrantes.size()) {
                        Palestrante palestrante = palestrantes.get(indexPalestrante);
    
                        System.out.print("Insira o E-mail: ");
                        String email = sc.nextLine().trim();
                        boolean emailDisponivel = palestrantes.stream()
                                .noneMatch(a -> a.getEmail().equalsIgnoreCase(email)) ||
                                palestrante.getEmail().equalsIgnoreCase(email);
    
                        if (emailDisponivel) {
                            System.out.print("Insira o Nome: ");
                            String nome = sc.nextLine().trim();
    
                            System.out.print("Insira o seu Endereço: ");
                            String endereco = sc.nextLine().trim();
    
                            System.out.print("Insira a especialidade do palestrante: ");
                            String especialidade = sc.nextLine().trim();
    
                            ArrayList<String> telefones = new ArrayList<>();
                            while (true) {
                                System.out.print("Insira o " + (telefones.size() + 1) + "º Telefone: ");
                                String telefone = sc.nextLine().trim();
                                telefones.add(telefone);
    
                                int stop;
                                while (true) {
                                    System.out.print("Gostaria de inserir mais um Telefone (1 - Sim, 2 - Não): ");
                                    String stopInput = sc.nextLine().trim();
                                    try {
                                        stop = Integer.parseInt(stopInput);
                                        if (stop == 1 || stop == 2) {
                                            break;
                                        } else {
                                            System.out.println("Valor inválido, insira novamente.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Entrada inválida, insira um número.");
                                    }
                                }
                                if (stop == 2) {
                                    break;
                                }
                            }
    
                            palestrante.setEmail(email);
                            palestrante.setNome(nome);
                            palestrante.setEndereco(endereco);
                            palestrante.setEspecialidade(especialidade);
                            palestrante.setTelefone(telefones);
    
                            System.out.println("\nEdição feita com sucesso\n");
                            break;
                        } else {
                            throw new Exception("Email já utilizado");
                        }
                    } else if (indexPalestrante == -1) {
                        System.out.println("Operação cancelada.");
                        break;
                    } else {
                        System.out.println("Valor inválido, insira um número válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, insira um número.");
                }
            }
        } else {
            throw new Exception("Nenhum palestrante cadastrado");
        }
    }    

    public void Inscricao(Scanner sc) throws Exception {
        if (!participantes.isEmpty() && !palestras.isEmpty()) {
            System.out.println("Participantes: ");
            for (Participante participante : participantes) {
                System.out.println("\nNúmero: " + participantes.indexOf(participante));
                System.out.println("Código do Participante: " + participante.getCodigo());
                System.out.println("Nome: " + participante.getNome());
                System.out.println("Email: " + participante.getEmail());
                System.out.println("Endereço: " + participante.getEndereco() + "\n");
            }
    
            while (true) {
                System.out.print("Insira o Número do participante a ser inscrito ou -1 para cancelar operação: ");
                String input = sc.nextLine().trim();
                try {
                    int indexParticipante = Integer.parseInt(input);
                    if (indexParticipante >= 0 && indexParticipante < participantes.size()) {
                        Participante participante = participantes.get(indexParticipante);
    
                        System.out.println("Palestras: ");
                        for (Palestra palestra : palestras) {
                            System.out.println("\nNúmero: " + palestras.indexOf(palestra));
                            System.out.println("Nome: " + palestra.getNome());
                            System.out.println("Quantidade de Vagas: " + palestra.getVagas());
                            System.out.println("Início: " + palestra.getInicio());
                            System.out.println("Fim: " + palestra.getFim());
                            System.out.println("Descrição: " + palestra.getDescricao() + "\n");
                        }
    
                        while (true) {
                            System.out.print("Insira o Número da palestra ou -1 para cancelar operação: ");
                            String palestraInput = sc.nextLine().trim();
                            try {
                                int indexPalestra = Integer.parseInt(palestraInput);
                                if (indexPalestra >= 0 && indexPalestra < palestras.size()) {
                                    Palestra palestra = palestras.get(indexPalestra);
    
                                    if (inscricoes.stream().filter(a -> a.getPalestra().equals(palestra))
                                            .count() < palestra.getVagas()) {
                                        if (inscricoes.stream()
                                                .noneMatch(a -> a.getPalestra().equals(palestra) && a.getParticipante().equals(participante))) {
                                            Inscricao inscricao = new Inscricao(participante, palestra);
                                            inscricoes.add(inscricao);
                                            System.out.println("\nInscrição feita com sucesso\n");
                                        } else {
                                            System.out.println("Esse participante já está inscrito nessa palestra");
                                        }
                                    } else {
                                        System.out.println("A palestra não possui mais vagas");
                                    }
                                    return;
                                } else if (indexPalestra == -1) {
                                    break;
                                } else {
                                    System.out.println("Valor inválido, insira novamente");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida, insira um número.");
                            }
                        }
                        break;
                    } else if (indexParticipante == -1) {
                        return;
                    } else {
                        System.out.println("Valor inválido, insira novamente");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, insira um número.");
                }
            }
        } else if (participantes.isEmpty()) {
            throw new Exception("Nenhum participante cadastrado");
        } else {
            throw new Exception("Nenhuma palestra cadastrada");
        }
    }    

    public void RemoverInscricao(Scanner sc) throws Exception {
        if (!participantes.isEmpty() && !inscricoes.isEmpty()) {
            System.out.println("Participantes: ");
            for (Participante participante : participantes) {
                System.out.println("\nNúmero: " + participantes.indexOf(participante));
                System.out.println("Código do Participante: " + participante.getCodigo());
                System.out.println("Nome: " + participante.getNome());
                System.out.println("Email: " + participante.getEmail());
                System.out.println("Endereço: " + participante.getEndereco() + "\n");
            }
            
            while (true) {
                System.out.print("Insira o Número do participante ou -1 para cancelar operação: ");
                String input = sc.nextLine().trim();
                try {
                    int indexParticipante = Integer.parseInt(input);
                    if (indexParticipante >= 0 && indexParticipante < participantes.size()) {
                        Participante participante = participantes.get(indexParticipante);
                        ArrayList<Inscricao> tmpInscricoes = inscricoes.stream()
                                .filter(a -> a.getParticipante().equals(participante))
                                .collect(Collectors.toCollection(ArrayList::new));
    
                        if (!tmpInscricoes.isEmpty()) {
                            System.out.println("Inscrições: ");
                            for (Inscricao inscricao : tmpInscricoes) {
                                System.out.println("\nNúmero: " + inscricoes.indexOf(inscricao));
                                System.out.println("Nome: " + inscricao.getPalestra().getNome());
                                System.out.println("Descrição: " + inscricao.getPalestra().getDescricao());
                                System.out.println("Capacidade de vagas: " + inscricao.getPalestra().getVagas());
                                System.out.println("Início: " + inscricao.getPalestra().getInicio());
                                System.out.println("Fim: " + inscricao.getPalestra().getFim());
    
                                Apresentacao apresentacao = apresentacoes.stream()
                                        .filter(a -> a.getPalestra().equals(inscricao.getPalestra()))
                                        .findAny()
                                        .orElse(null);
                                if (apresentacao != null) {
                                    System.out.println("Palestrante responsável: ");
                                    System.out.println("Nome: " + apresentacao.getPalestrante().getNome());
                                    System.out.println("Email: " + apresentacao.getPalestrante().getEmail());
                                }
    
                                Alocacao alocacao = alocacoes.stream()
                                        .filter(a -> a.getPalestra().equals(inscricao.getPalestra()))
                                        .findAny()
                                        .orElse(null);
                                if (alocacao != null) {
                                    System.out.println("Local da palestra: ");
                                    System.out.println(alocacao.getLocal().getNome());
                                }
                                System.out.println("\n");
                            }
    
                            while (true) {
                                System.out.print("Insira o número da inscrição a ser removida ou -1 para cancelar a operação: ");
                                String inscricaoInput = sc.nextLine().trim();
                                try {
                                    int indexInscricao = Integer.parseInt(inscricaoInput);
                                    if (indexInscricao >= 0 && indexInscricao < tmpInscricoes.size()) {
                                        Inscricao inscricao = tmpInscricoes.get(indexInscricao);
                                        inscricoes.remove(inscricao);
                                        System.out.println("\nRemoção feita com sucesso\n");
                                        break;
                                    } else if (indexInscricao == -1) {
                                        break;
                                    } else {
                                        System.out.println("Valor inválido");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida, insira um número.");
                                }
                            }
                            break;
                        } else {
                            System.out.println("Esse participante não possui inscrições");
                            break;
                        }
                    } else if (indexParticipante == -1) {
                        break;
                    } else {
                        System.out.println("Valor inválido");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, insira um número.");
                }
            }
        } else if (participantes.isEmpty()) {
            throw new Exception("Nenhum participante cadastrado");
        } else {
            throw new Exception("Nenhuma inscrição cadastrada");
        }
    }    

    public void AdicionarLocal(Scanner sc) throws Exception {
        System.out.print("Insira o nome: ");
        String Nome = sc.nextLine().trim();
        if (locais.stream().filter(a -> a.getNome().equalsIgnoreCase(Nome))
                .collect(Collectors.toCollection(ArrayList::new)).isEmpty()) {
            
            System.out.print("Insira a capacidade: ");
            int Capacidade = 0;
            try {
                Capacidade = Integer.parseInt(sc.nextLine().trim());
                if (Capacidade <= 0) {
                    throw new Exception("Capacidade deve ser maior que zero.");
                }
            } catch (NumberFormatException e) {
                throw new Exception("Valor inválido para capacidade.");
            }
    
            ArrayList<String> Recursos = new ArrayList<>();
            while (true) {
                System.out.print("Insira o " + (Recursos.size() + 1) + "º Recurso: ");
                String tmp_recurso = sc.nextLine().trim();
                Recursos.add(tmp_recurso);
    
                int stop = 0;
                while (true) {
                    System.out.print("Gostaria de inserir mais um Recurso (1 - Sim, 2 - Não): ");
                    try {
                        stop = Integer.parseInt(sc.nextLine().trim());
                        if (stop == 1 || stop == 2) {
                            break;
                        } else {
                            System.out.println("Valor inválido, insira novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida, insira 1 ou 2.");
                    }
                }
                if (stop == 2) {
                    break;
                }
            }
    
            Local local = new Local(Nome, Capacidade);
            local.setRecursos(Recursos);
            locais.add(local);
            System.out.println("\nAdição feita com sucesso.\n");
    
        } else {
            throw new Exception("Nome inválido, já existe um local com esse nome.");
        }
    }    

    public void RemoverLocal(Scanner sc) throws Exception {
        if (!locais.isEmpty()) {
            System.out.println("Locais: ");
            for (Local local : locais) {
                System.out.println("\nNúmero: " + locais.indexOf(local));
                System.out.println("Nome: " + local.getNome());
                System.out.println("Capacidade: " + local.getCapacidade());
                if (!local.getRecursos().isEmpty()) {
                    System.out.println("Recursos: ");
                    for (String obj : local.getRecursos()) {
                        System.out.println(obj);
                    }
                }
                System.out.println("\n");
            }
    
            while (true) {
                System.out.print("Insira o Número do local a ser removido ou -1 para cancelar operação: ");
                int index_local = 0;
                try {
                    index_local = Integer.parseInt(sc.nextLine().trim());
                    if (index_local >= 0 && index_local < locais.size()) {
                        Local tmp = locais.get(index_local);
                        alocacoes.removeIf(a -> a.getLocal().equals(tmp));
                        locais.remove(tmp);
                        System.out.println("\nRemoção feita com sucesso\n");
                        return;
                    } else if (index_local == -1) {
                        return;
                    } else {
                        System.out.println("Valor inválido, insira novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, insira um número válido.");
                }
            }
        } else {
            throw new Exception("Nenhum local cadastrado.");
        }
    }    

    public void EditarLocal(Scanner sc) throws Exception {
        if (!locais.isEmpty()) {
            System.out.println("Locais: ");
            for (Local local : locais) {
                System.out.println("\nNúmero: " + locais.indexOf(local));
                System.out.println("Nome: " + local.getNome());
                System.out.println("Capacidade: " + local.getCapacidade());
                if (!local.getRecursos().isEmpty()) {
                    System.out.println("Recursos: ");
                    for (String obj : local.getRecursos()) {
                        System.out.println(obj);
                    }
                }
                System.out.println("\n");
            }
    
            while (true) {
                System.out.print("Insira o Número do local a ser editado ou -1 para cancelar operação: ");
                int index_local = 0;
                try {
                    index_local = Integer.parseInt(sc.nextLine().trim());
                    if (index_local >= 0 && index_local < locais.size()) {
                        Local tmp = locais.get(index_local);
    
                        // Captura o novo nome
                        System.out.println("Insira o nome: ");
                        String Nome = sc.nextLine();
                        if (locais.stream().filter(a -> a.getNome().equalsIgnoreCase(Nome))
                                .collect(Collectors.toList()).isEmpty() || tmp.getNome().equals(Nome)) {
    
                            // Captura os recursos
                            ArrayList<String> Recursos = new ArrayList<>();
                            while (true) {
                                System.out.println("Insira o " + (Recursos.size() + 1) + "º Recurso: ");
                                String tmp_recurso = sc.nextLine();
                                Recursos.add(tmp_recurso);
    
                                // Pergunta se quer inserir mais recursos
                                Integer stop = 0;
                                while (true) {
                                    System.out.print("Gostaria de inserir mais um Recurso(1 - Sim, 2 - Não): ");
                                    stop = Integer.parseInt(sc.nextLine().trim());
                                    if (stop == 1 || stop == 2) {
                                        break;
                                    } else {
                                        System.out.println("Valor inválido, insira novamente");
                                    }
                                }
    
                                if (stop == 2) {
                                    break;
                                }
                            }
    
                            tmp.setNome(Nome);
                            tmp.setRecursos(Recursos);
                            System.out.println("\nEdição feita com sucesso\n");
                            return;
                        } else {
                            throw new Exception("Nome inválido");
                        }
                    } else if (index_local == -1) {
                        return;
                    } else {
                        System.out.println("Valor inválido, insira novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida, insira um número válido.");
                }
            }
        } else {
            throw new Exception("Nenhum local cadastrado.");
        }
    }    

    public void AdicionarPalestra(Scanner sc) throws Exception {
        System.out.println("Insira a data (yyyy-MM-dd): ");
        String tmp_data = sc.nextLine();
        
        System.out.println("Insira a hora de inicio(HH:mm)");
        String hora_inicio = sc.nextLine();
        
        System.out.println("Insira a hora de finalização (HH:mm): ");
        String hora_fim = sc.nextLine();
    
        // Cria o LocalDateTime para início e fim
        String tmp_LocalDateTime = tmp_data + "T" + hora_inicio;
        LocalDateTime inicio = LocalDateTime.parse(tmp_LocalDateTime);
        tmp_LocalDateTime = tmp_data + "T" + hora_fim;
        LocalDateTime fim = LocalDateTime.parse(tmp_LocalDateTime);
    
        if (inicio.isBefore(fim)) {
            System.out.println("Insira o nome: ");
            String Nome = sc.nextLine();
            
            System.out.println("Insira a descrição: ");
            String Descricao = sc.nextLine();
            
            System.out.println("Insira a quantidade de vagas");
            int vagas = Integer.parseInt(sc.nextLine());
    
            if (vagas > 0) {
                System.out.println("A palestra emite certificado? (1 - Sim, 2 - Não): ");
                int tmp_boolean = sc.nextInt();
                sc.nextLine();  // Limpar a linha pendente do nextInt()
                boolean EmiteCertificado = (tmp_boolean == 1);
    
                Palestra palestra = new Palestra(Nome, Descricao, inicio, fim, vagas, EmiteCertificado);
                palestras.add(palestra);
    
                // Adiciona Local à palestra
                System.out.println("Adicionar local à palestra? (1 - Sim, 2 - Não): ");
                int choice = sc.nextInt();
                sc.nextLine();  // Limpar a linha pendente
                if (choice == 1) {
                    ArrayList<Local> tmp_Locais = locais.stream()
                            .filter(a -> a.getCapacidade() >= vagas)
                            .collect(Collectors.toCollection(ArrayList::new));
                    
                    if (!tmp_Locais.isEmpty()) {
                        System.out.println("Locais: ");
                        for (Local local : tmp_Locais) {
                            System.out.println("\nNúmero: " + tmp_Locais.indexOf(local));
                            System.out.println("Nome: " + local.getNome());
                            System.out.println("Capacidade: " + local.getCapacidade());
                            if (!local.getRecursos().isEmpty()) {
                                System.out.println("Recursos: ");
                                for (String obj : local.getRecursos()) {
                                    System.out.println(obj);
                                }
                            }
                            System.out.println("\n");
                        }
    
                        System.out.println("Insira o Número do local a ser atrelado ou -1 para cancelar operação: ");
                        int index_local = sc.nextInt();
                        sc.nextLine();  // Limpar a linha pendente
                        if (index_local >= 0 && index_local < tmp_Locais.size()) {
                            Local local = tmp_Locais.get(index_local);
                            ArrayList<Alocacao> tmp_alocacoes = alocacoes.stream()
                                    .filter(a -> a.getLocal().equals(local))
                                    .collect(Collectors.toCollection(ArrayList::new));
    
                            if (tmp_alocacoes.isEmpty() || tmp_alocacoes.stream()
                                    .noneMatch(a -> (a.getPalestra().getInicio().isBefore(inicio)
                                            && a.getPalestra().getFim().isAfter(inicio))
                                            || (a.getPalestra().getInicio().isBefore(fim)
                                            && a.getPalestra().getFim().isAfter(fim))
                                            || (inicio.isBefore(a.getPalestra().getInicio())
                                            && fim.isAfter(a.getPalestra().getInicio()))
                                            || (inicio.isBefore(a.getPalestra().getFim())
                                            && fim.isAfter(a.getPalestra().getFim())))) {
    
                                Alocacao alocacao = new Alocacao(local, palestra);
                                alocacoes.add(alocacao);
                                System.out.println("\nRelação criada com sucesso\n");
                            } else {
                                System.out.println("Conflito com horários");
                            }
                        }
                    } else {
                        System.out.println("Sem locais com capacidade suficiente");
                    }
                }
    
                // Adiciona Palestrante à palestra
                System.out.println("Adicionar palestrante à palestra? (1 - Sim, 2 - Não): ");
                choice = sc.nextInt();
                sc.nextLine();  // Limpar a linha pendente
                if (choice == 1) {
                    if (!palestrantes.isEmpty()) {
                        System.out.println("Palestrantes: ");
                        for (Palestrante palestrante : palestrantes) {
                            System.out.println("\nNúmero: " + palestrantes.indexOf(palestrante));
                            System.out.println("Nome: " + palestrante.getNome());
                            System.out.println("Email: " + palestrante.getEmail());
                            System.out.println("Endereço: " + palestrante.getEndereco());
                            System.out.println("Especialidade: " + palestrante.getEspecialidade() + "\n");
                        }
    
                        System.out.println("Insira o Número do palestrante a ser atrelado ou -1 para cancelar operação: ");
                        int index_palestrante = sc.nextInt();
                        sc.nextLine();  // Limpar a linha pendente
                        if (index_palestrante >= 0 && index_palestrante < palestrantes.size()) {
                            Palestrante palestrante = palestrantes.get(index_palestrante);
                            Apresentacao apresentacao = new Apresentacao(palestrante, palestra);
                            apresentacoes.add(apresentacao);
                            System.out.println("\nRelação criada com sucesso\n");
                        }
                    } else {
                        System.out.println("Sem palestrantes adicionados");
                    }
                }
    
                System.out.println("\nAdição feita com sucesso\n");
            } else {
                System.out.println("Quantidade de vagas deve ser maior que 0");
            }
        } else {
            throw new Exception("Horários inválidos");
        }
    }        

    public void RemoverPalestra(Scanner sc) throws Exception {
        if (!palestras.isEmpty()) {
            System.out.println("Palestras: ");
            for (Palestra palestra : palestras) {
                System.out.println("\nNúmero: " + palestras.indexOf(palestra));
                System.out.println("Nome: " + palestra.getNome());
                System.out.println("Descrição: " + palestra.getDescricao());
                System.out.println("Capacidade de vagas: " + palestra.getVagas());
                System.out.println("Início: " + palestra.getInicio());
                System.out.println("Fim: " + palestra.getFim());
    
                // Exibe o palestrante
                Apresentacao apresentacao = apresentacoes.stream()
                        .filter(a -> a.getPalestra().equals(palestra))
                        .findAny().orElse(null);
                if (apresentacao != null) {
                    System.out.println("Palestrante responsável: ");
                    System.out.println("Nome: " + apresentacao.getPalestrante().getNome());
                    System.out.println("Email: " + apresentacao.getPalestrante().getEmail());
                }
    
                // Exibe o local da palestra
                Alocacao alocacao = alocacoes.stream()
                        .filter(a -> a.getPalestra().equals(palestra))
                        .findAny().orElse(null);
                if (alocacao != null) {
                    System.out.println("Local da palestra: ");
                    System.out.println(alocacao.getLocal().getNome());
                }
                System.out.println("\n");
            }
    
            while (true) {
                System.out.println("Insira o Número da palestra a ser removida ou -1 para cancelar operação: ");
                int index_palestra = sc.nextInt();
                sc.nextLine();  // Limpar a linha pendente após nextInt()
                
                if (index_palestra >= 0 && index_palestra < palestras.size()) {
                    Palestra palestra = palestras.get(index_palestra);
                    
                    // Remove a palestra das respectivas listas
                    apresentacoes.removeIf(a -> a.getPalestra().equals(palestra));
                    alocacoes.removeIf(a -> a.getPalestra().equals(palestra));
                    inscricoes.removeIf(a -> a.getPalestra().equals(palestra));
                    palestras.remove(palestra);
    
                    System.out.println("\nRemoção feita com sucesso\n");
                    return;
                } else if (index_palestra == -1) {
                    return; // Operação cancelada
                } else {
                    System.out.println("Valor inválido, insira novamente");
                }
            }
        } else {
            throw new Exception("Nenhuma palestra cadastrada");
        }
    }        

    public void EditarPalestra(Scanner sc) throws Exception {
        if (!palestras.isEmpty()) {
            // Exibe as palestras
            exibirPalestras();
    
            while (true) {
                System.out.println("Insira o Número da palestra a ser editada ou -1 para cancelar operação: ");
                int index_palestra = sc.nextInt();
                sc.nextLine();  // Limpar a linha pendente após nextInt()
    
                if (index_palestra >= 0 && index_palestra < palestras.size()) {
                    Palestra palestra = palestras.get(index_palestra);
                    Palestra palestra_de_busca = new Palestra(palestra.getNome(), palestra.getDescricao(),
                            palestra.getInicio(), palestra.getFim(), palestra.getVagas(),
                            palestra.getEmiteCertificado());
    
                    // Leitura de nova data e hora
                    System.out.println("Insira a data(yyyy-MM-dd): ");
                    String tmp_data = sc.nextLine();
                    System.out.println("Insira a hora de início(HH:mm): ");
                    String hora_inicio = sc.nextLine();
                    System.out.println("Insira a hora de finalização(HH:mm): ");
                    String hora_fim = sc.nextLine();
    
                    // Cria o LocalDateTime para início e fim
                    String tmp_LocalDateTime = tmp_data + "T" + hora_inicio;
                    LocalDateTime inicio = LocalDateTime.parse(tmp_LocalDateTime);
                    tmp_LocalDateTime = tmp_data + "T" + hora_fim;
                    LocalDateTime fim = LocalDateTime.parse(tmp_LocalDateTime);
    
                    if (inicio.isBefore(fim)) {
                        // Alteração dos dados da palestra
                        alterarPalestra(sc, palestra, inicio, fim);
    
                        // Alterar local
                        if (alterarLocal(sc, palestra, palestra_de_busca, inicio, fim)) {
                            // Alterar palestrante
                            alterarPalestrante(sc, palestra_de_busca);
                        }
                        System.out.println("\nEdição feita com sucesso\n");
                        return;
                    } else {
                        throw new Exception("Horários inválidos");
                    }
                } else if (index_palestra == -1) {
                    return; // Cancelamento
                } else {
                    System.out.println("Valor inválido, insira novamente");
                }
            }
        } else {
            throw new Exception("Nenhuma palestra cadastrada");
        }
    }
    
    // Exibe as palestras e seus detalhes
    private void exibirPalestras() {
        System.out.println("Palestras: ");
        for (Palestra palestra : palestras) {
            System.out.println("\nNúmero: " + palestras.indexOf(palestra));
            System.out.println("Nome: " + palestra.getNome());
            System.out.println("Descrição: " + palestra.getDescricao());
            System.out.println("Capacidade de vagas: " + palestra.getVagas());
            System.out.println("Início: " + palestra.getInicio());
            System.out.println("Fim: " + palestra.getFim());
    
            // Apresentação
            Apresentacao apresentacao = apresentacoes.stream()
                    .filter(a -> a.getPalestra().equals(palestra))
                    .findAny().orElse(null);
            if (apresentacao != null) {
                System.out.println("Palestrante responsável: ");
                System.out.println("Nome: " + apresentacao.getPalestrante().getNome());
                System.out.println("Email: " + apresentacao.getPalestrante().getEmail());
            }
    
            // Local
            Alocacao alocacao = alocacoes.stream()
                    .filter(a -> a.getPalestra().equals(palestra)).findAny().orElse(null);
            if (alocacao != null) {
                System.out.println("Local da palestra: ");
                System.out.println(alocacao.getLocal().getNome());
            }
            System.out.println("\n");
        }
    }
    
    // Lê a data e a hora
    private LocalDateTime lerDataHora(Scanner sc, String msgData, String msgHora) {
        // Exibe a mensagem para o usuário e lê a entrada para a data
        System.out.print(msgData);
        String tmp_data = sc.nextLine();
        
        // Exibe a mensagem para o usuário e lê a entrada para a hora
        System.out.print(msgHora);
        String hora_inicio = sc.nextLine();
        
        // Concatena a data e hora em formato ISO
        String tmp_LocalDateTime = tmp_data + "T" + hora_inicio;
        
        // Converte para LocalDateTime
        return LocalDateTime.parse(tmp_LocalDateTime);
    }
    
    
    // Altera os dados da palestra
    private void alterarPalestra(Scanner sc, Palestra palestra, LocalDateTime inicio, LocalDateTime fim) {
        // Solicita o nome e lê a entrada
        System.out.print("Insira o nome: ");
        String nome = sc.nextLine();
        
        // Solicita a descrição e lê a entrada
        System.out.print("Insira a Descrição: ");
        String descricao = sc.nextLine();
        
        // Solicita se emite certificado e lê a entrada
        System.out.print("A palestra emite certificado ?(1 - Sim, 2 - Não): ");
        Integer tmp_boolean = Integer.parseInt(sc.nextLine());
        boolean emiteCertificado = (tmp_boolean == 1);
        
        // Atualiza os dados da palestra
        palestra.setNome(nome);
        palestra.setDescricao(descricao);
        palestra.setInicio(inicio);
        palestra.setFim(fim);
        palestra.setEmiteCertificado(emiteCertificado);
    }    
    
    // Altera o local da palestra
    private boolean alterarLocal(Scanner sc, Palestra palestra, Palestra palestra_de_busca, LocalDateTime inicio, LocalDateTime fim) {
        System.out.println("Alterar local da palestra ?(1 - Sim, 2 - Não): ");
        int choice = sc.nextInt();
        sc.nextLine(); // Limpar a linha após nextInt()
    
        if (choice == 1) {
            ArrayList<Local> tmp_Locais = locais.stream()
                    .filter(a -> a.getCapacidade() >= palestra.getVagas())
                    .collect(Collectors.toCollection(ArrayList::new));
    
            if (!tmp_Locais.isEmpty()) {
                // Exibe os locais disponíveis
                exibirLocais(sc, tmp_Locais, palestra, inicio, fim);
                return true;
            } else {
                System.out.println("Sem locais adicionados");
            }
        }
        return false;
    }
    
    // Exibe os locais disponíveis e permite escolher um novo local
    private void exibirLocais(Scanner sc, ArrayList<Local> tmp_Locais, Palestra palestra, LocalDateTime inicio, LocalDateTime fim) {
        System.out.println("Locais: ");
        for (Local local : tmp_Locais) {
            System.out.println("\nNúmero: " + locais.indexOf(local));
            System.out.println("Nome: " + local.getNome());
            System.out.println("Capacidade: " + local.getCapacidade());
            if (!local.getRecursos().isEmpty()) {
                System.out.println("Recursos: ");
                for (String obj : local.getRecursos()) {
                    System.out.println(obj);
                }
            }
            ArrayList<Alocacao> tmp_alocacoes = alocacoes.stream()
                    .filter(a -> a.getLocal().equals(local) && !aTemConflito(a, palestra, inicio, fim))
                    .collect(Collectors.toCollection(ArrayList::new));
            
            if (tmp_alocacoes.isEmpty()) {
                System.out.println("Esse local não possui eventos");
            } else {
                System.out.println("Palestras do local: ");
                for (Alocacao alocacao : tmp_alocacoes) {
                    System.out.println("Nome: " + alocacao.getPalestra().getNome());
                    System.out.println("Descrição: " + alocacao.getPalestra().getDescricao());
                    System.out.println("Início: " + alocacao.getPalestra().getInicio());
                    System.out.println("Fim: " + alocacao.getPalestra().getFim());
                }
            }
            System.out.println("\n");
        }
    }
    
    // Verifica se há conflito de horários de uma palestra com a alocação
    private boolean aTemConflito(Alocacao a, Palestra palestra, LocalDateTime inicio, LocalDateTime fim) {
        return a.getPalestra().getInicio().isBefore(fim) && a.getPalestra().getFim().isAfter(inicio);
    }
    
    // Altera o palestrante da palestra
    private void alterarPalestrante(Scanner sc, Palestra palestra_de_busca) {
        System.out.println("Alterar palestrante da palestra ?(1 - Sim, 2 - Não): ");
        int choice = sc.nextInt();
        sc.nextLine(); // Limpar a linha após nextInt()
    
        if (choice == 1) {
            if (!palestrantes.isEmpty()) {
                System.out.println("Palestrantes: ");
                for (Palestrante palestrante : palestrantes) {
                    System.out.println("\nNúmero: " + palestrantes.indexOf(palestrante));
                    System.out.println("Nome: " + palestrante.getNome());
                    System.out.println("Email: " + palestrante.getEmail());
                    System.out.println("Endereço: " + palestrante.getEndereco());
                    System.out.println("Especialidade: " + palestrante.getEspecialidade() + "\n");
                }
                while (true) {
                    System.out.println("Insira o Número do palestrante a ser atrelado ou -1 para cancelar operação: ");
                    int index_palestrante = sc.nextInt();
                    sc.nextLine(); // Limpar a linha após nextInt()
    
                    if (index_palestrante >= 0 && index_palestrante < palestrantes.size()) {
                        Palestrante palestrante = palestrantes.get(index_palestrante);
                        apresentacoes.removeIf(a -> a.getPalestra().equals(palestra_de_busca));
                        Apresentacao apresentacao = new Apresentacao(palestrante, palestra_de_busca);
                        apresentacoes.add(apresentacao);
                        System.out.println("\nRelação alterada com sucesso\n");
                        break;
                    } else if (index_palestrante == -1) {
                        break;
                    } else {
                        System.out.println("Valor inválido, insira novamente");
                    }
                }
            } else {
                System.out.println("Sem palestrantes cadastrados");
            }
        }
    }        

    public void ListaPresenca(Scanner sc) throws Exception {
        if (!palestras.isEmpty()) {
            // Exibe as palestras
            exibirPalestras();
            // Permite o usuário escolher qual palestra para imprimir a lista de presença
            selecionarPalestraParaLista(sc);
        } else {
            throw new Exception("Nenhuma palestra cadastrada");
        }
    }
    // Obtém a apresentação associada à palestra
    private Apresentacao getApresentacao(Palestra palestra) {
        return apresentacoes.stream()
                .filter(a -> a.getPalestra().equals(palestra))
                .findAny().orElse(null);
    }
    
    // Obtém a alocação associada à palestra
    private Alocacao getAlocacao(Palestra palestra) {
        return alocacoes.stream()
                .filter(a -> a.getPalestra().equals(palestra))
                .findAny().orElse(null);
    }
    
    // Permite o usuário selecionar a palestra e imprimir a lista de presença
    private void selecionarPalestraParaLista(Scanner sc) {
        while (true) {
            System.out.println("Imprimir lista de presença de qual palestra ?(-1 para cancelar operação): ");
            int index_palestra = sc.nextInt();
            sc.nextLine(); // Limpar o buffer após nextInt()
    
            if (index_palestra >= 0 && index_palestra < palestras.size()) {
                imprimirListaPresenca(palestras.get(index_palestra));
            } else if (index_palestra == -1) {
                break; // Cancelamento da operação
            } else {
                System.out.println("Valor inválido");
            }
        }
    }
    
    // Imprime a lista de presença para a palestra escolhida
    private void imprimirListaPresenca(Palestra palestra) {
        ArrayList<Inscricao> tmp_inscricoes = inscricoes.stream()
                .filter(a -> a.getPalestra().equals(palestra))
                .collect(Collectors.toCollection(ArrayList::new));
    
        if (!tmp_inscricoes.isEmpty()) {
            System.out.println("Inscrições da palestra: ");
            for (Inscricao inscricao : tmp_inscricoes) {
                Participante participante = inscricao.getParticipante();
                System.out.println("\nCódigo do Participante: " + participante.getCodigo());
                System.out.println("Nome: " + participante.getNome());
                System.out.println("Email: " + participante.getEmail());
                System.out.println("Endereço: " + participante.getEndereco() + "\n");
            }
        } else {
            System.out.println("Essa palestra não possui inscrições");
        }
    }    

    public void ListaLocalEPalestras() throws Exception {
        if (!locais.isEmpty()) {
            for (Local local : locais) {
                System.out.println("Local: ");
                System.out.println("\nNúmero: " + locais.indexOf(local));
                System.out.println("Nome: " + local.getNome());
                System.out.println("Capacidade: " + local.getCapacidade());
                if (!local.getRecursos().isEmpty()) {
                    System.out.println("Recursos: ");
                    for (String obj : local.getRecursos()) {
                        System.out.println(obj);
                    }
                }
                ArrayList<Alocacao> tmp_alocacoes = alocacoes.stream().filter(a -> a.getLocal().equals(local))
                        .collect(Collectors.toCollection(ArrayList::new));
                if (!tmp_alocacoes.isEmpty()) {
                    System.out.println("\nPalestras do local: ");
                    for (Alocacao alocacao : tmp_alocacoes) {
                        System.out.println("Nome: " + alocacao.getPalestra().getNome());
                        System.out.println("Descrição: " + alocacao.getPalestra().getDescricao());
                        System.out.println("Capacidade de vagas: " + alocacao.getPalestra().getVagas());
                        System.out.println("Início: " + alocacao.getPalestra().getInicio());
                        System.out.println("Fim: " + alocacao.getPalestra().getFim());
                        Apresentacao apresentacao = apresentacoes.stream()
                                .filter(a -> a.getPalestra().equals(alocacao.getPalestra())).findAny().orElse(null);
                        if (apresentacao != null) {
                            System.out.println("Palestrante responsável: ");
                            System.out.println("Nome: " + apresentacao.getPalestrante().getNome());
                            System.out.println("Email: " + apresentacao.getPalestrante().getEmail());
                        }
                    }
                }
                System.out.println("\n");
            }
        } else {
            throw new Exception("Nenhum local cadastrado");
        }
    }

    public void PalestrasDeParticipante(Scanner sc) throws Exception {
        if (!participantes.isEmpty()) {
            // Exibe os participantes
            exibirParticipantes();
    
            // Permite o usuário escolher qual participante para exibir as palestras
            selecionarParticipanteParaPalestras(sc);
        } else {
            throw new Exception("Nenhum participante cadastrado");
        }
    }
    
    // Exibe todos os participantes e seus detalhes
    private void exibirParticipantes() {
        System.out.println("Participantes: ");
        for (Participante participante : participantes) {
            System.out.println("\nNúmero: " + participantes.indexOf(participante));
            System.out.println("Código do Participante: " + participante.getCodigo());
            System.out.println("Nome: " + participante.getNome());
            System.out.println("Email: " + participante.getEmail());
            System.out.println("Endereço: " + participante.getEndereco() + "\n");
        }
    }
    
    // Permite o usuário selecionar o participante e exibir suas palestras
    private void selecionarParticipanteParaPalestras(Scanner sc) {
        while (true) {
            System.out.println("Imprimir lista de palestras de qual participante ?(-1 para cancelar operação): ");
            int index_participante = sc.nextInt();
            sc.nextLine(); // Limpar o buffer após nextInt()
    
            if (index_participante >= 0 && index_participante < participantes.size()) {
                imprimirPalestrasParticipante(participantes.get(index_participante));
            } else if (index_participante == -1) {
                break; // Cancelamento da operação
            } else {
                System.out.println("Valor inválido");
            }
        }
    }
    
    // Imprime as palestras do participante escolhido
    private void imprimirPalestrasParticipante(Participante participante) {
        ArrayList<Inscricao> tmp_inscricoes = inscricoes.stream()
                .filter(a -> a.getParticipante().equals(participante))
                .collect(Collectors.toCollection(ArrayList::new));
    
        if (!tmp_inscricoes.isEmpty()) {
            System.out.println("Palestras: ");
            for (Inscricao inscricao : tmp_inscricoes) {
                exibirDetalhesPalestra(inscricao);
            }
        } else {
            System.out.println("O participante não possui inscrições");
        }
    }
    
    // Exibe os detalhes de cada palestra associada à inscrição
    private void exibirDetalhesPalestra(Inscricao inscricao) {
        Palestra palestra = inscricao.getPalestra();
        System.out.println("\nNome: " + palestra.getNome());
        System.out.println("Descrição: " + palestra.getDescricao());
        System.out.println("Capacidade de vagas: " + palestra.getVagas());
        System.out.println("Início: " + palestra.getInicio());
        System.out.println("Fim: " + palestra.getFim());
    
        // Palestrante responsável
        Apresentacao apresentacao = getApresentacao(palestra);
        if (apresentacao != null) {
            System.out.println("Palestrante responsável: ");
            System.out.println("Nome: " + apresentacao.getPalestrante().getNome());
            System.out.println("Email: " + apresentacao.getPalestrante().getEmail());
        }
    
        // Local da palestra
        Alocacao alocacao = getAlocacao(palestra);
        if (alocacao != null) {
            System.out.println("Local da palestra: ");
            System.out.println(alocacao.getLocal().getNome());
        }
        System.out.println("\n");
    }

    public ArrayList<Certificado> EmitirCertificados(Scanner sc) throws Exception {
        ArrayList<Palestra> palestras_com_certificados = filtrarPalestrasComCertificado();
        
        if (!palestras_com_certificados.isEmpty()) {
            exibirPalestrasComCertificado(palestras_com_certificados);
    
            return selecionarPalestraParaEmitirCertificados(sc, palestras_com_certificados);
        } else {
            throw new Exception("Nenhuma palestra com possível emissão de certificado");
        }
    }
    
    // Filtra e retorna palestras que emitem certificado
    private ArrayList<Palestra> filtrarPalestrasComCertificado() {
        return palestras.stream()
                .filter(Palestra::getEmiteCertificado)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    // Exibe as palestras com emissão de certificado
    private void exibirPalestrasComCertificado(ArrayList<Palestra> palestras_com_certificados) {
        System.out.println("Palestras: ");
        for (Palestra palestra : palestras_com_certificados) {
            exibirDetalhesPalestra(palestra);
        }
    }
    
    // Exibe os detalhes de uma palestra
    private void exibirDetalhesPalestra(Palestra palestra) {
        System.out.println("\nNúmero: " + palestras.indexOf(palestra));
        System.out.println("Nome: " + palestra.getNome());
        System.out.println("Descrição: " + palestra.getDescricao());
        System.out.println("Capacidade de vagas: " + palestra.getVagas());
        System.out.println("Início: " + palestra.getInicio());
        System.out.println("Fim: " + palestra.getFim());
    
        Apresentacao apresentacao = getApresentacao(palestra);
        if (apresentacao != null) {
            System.out.println("Palestrante responsável: ");
            System.out.println("Nome: " + apresentacao.getPalestrante().getNome());
            System.out.println("Email: " + apresentacao.getPalestrante().getEmail());
        }
    
        Alocacao alocacao = getAlocacao(palestra);
        if (alocacao != null) {
            System.out.println("Local da palestra: ");
            System.out.println(alocacao.getLocal().getNome());
        }
        System.out.println("\n");
    }
    
    // Permite ao usuário selecionar a palestra para emitir certificados
    private ArrayList<Certificado> selecionarPalestraParaEmitirCertificados(Scanner sc, ArrayList<Palestra> palestras_com_certificados) {
        while (true) {
            System.out.println("Emitir certificados de qual palestra ?(-1 para cancelar operação): ");
            int index_palestra = sc.nextInt();
            sc.nextLine(); // Limpar o buffer após nextInt()
    
            if (index_palestra >= 0 && index_palestra < palestras_com_certificados.size()) {
                return emitirCertificados(sc, palestras_com_certificados.get(index_palestra));
            } else if (index_palestra == -1) {
                break;
            } else {
                System.out.println("Valor inválido");
            }
        }
        return null;
    }
    
    // Emite os certificados para a palestra selecionada
    private ArrayList<Certificado> emitirCertificados(Scanner sc, Palestra palestra) {
        ArrayList<Inscricao> tmp_inscricoes = filtrarInscricoesPorPalestra(palestra);
    
        if (!tmp_inscricoes.isEmpty()) {
            // Solicita a descrição do certificado e lê a entrada
            System.out.print("Insira a descrição do certificado: ");
            String descricaoCertificado = sc.nextLine();
            
            ArrayList<Certificado> certificados = new ArrayList<>();
            System.out.println("Certificados: ");
            
            // Emite e exibe os detalhes dos certificados
            for (Inscricao inscricao : tmp_inscricoes) {
                certificados.add(criarCertificado(inscricao, descricaoCertificado));
                exibirDetalhesCertificado(inscricao, descricaoCertificado);
            }
            
            System.out.println("\nEmissão feita com sucesso\n");
            return certificados;
        } else {
            System.out.println("Essa palestra não possui inscrições");
        }
        return null;
    }    
    
    // Filtra inscrições para uma palestra específica
    private ArrayList<Inscricao> filtrarInscricoesPorPalestra(Palestra palestra) {
        return inscricoes.stream()
                .filter(a -> a.getPalestra().equals(palestra))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    // Cria um certificado para o participante e palestra
    private Certificado criarCertificado(Inscricao inscricao, String descricaoCertificado) {
        return new Certificado(inscricao.getPalestra(), inscricao.getParticipante(), descricaoCertificado);
    }
    
    // Exibe os detalhes do certificado emitido
    private void exibirDetalhesCertificado(Inscricao inscricao, String descricaoCertificado) {
        System.out.println("\nParticipante: ");
        System.out.println("Nome: " + inscricao.getParticipante().getNome());
        System.out.println("Email: " + inscricao.getParticipante().getEmail());
        System.out.println("Endereço: " + inscricao.getParticipante().getEndereco());
        System.out.println("Palestra concluída:");
        System.out.println("Nome: " + inscricao.getPalestra().getNome());
        System.out.println("Descrição: " + inscricao.getPalestra().getDescricao());
        System.out.println("Início: " + inscricao.getPalestra().getInicio());
        System.out.println("Fim: " + inscricao.getPalestra().getFim());
    
        Apresentacao apresentacao = getApresentacao(inscricao.getPalestra());
        if (apresentacao != null) {
            System.out.println("Nome do palestrante: " + apresentacao.getPalestrante().getNome());
        }
        System.out.println("Descrição do certificado: " + descricaoCertificado + "\n");
    }
}