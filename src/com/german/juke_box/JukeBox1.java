package com.german.juke_box;

import java.io.*;
import java.util.*;

    //  Программа считывает из файла набор треков в формате "Название/Исполнитель/Рейтинг/БПМ
    //  Затем добавляет их в HashSet для сортировки по названию и удаления дубликатов.

public class JukeBox1 {
    ArrayList<Song> songList = new ArrayList<>();

    public static void main(String[] args) {
        new JukeBox1().go();
    }

    void go() {
        getSongs();
        System.out.println(songList);
        Collections.sort(songList, new ArtistCompare());
        System.out.println(songList);
        TreeSet<Song> songSet = new TreeSet<>();
        songSet.addAll(songList);
        System.out.println(songSet);
    }

    // Получение списка треков из файла
    void getSongs() {
        try {
            File file = new File("SongList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                addSong(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  Создание объектов Song и добавление их в ArrayList
    void addSong(String lineToParse) {
        String[] tokens = lineToParse.split("/");

        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
    }

    //  Компаратор для реализации сортировки по исполнителю через ArrayList и метод Sort с двумя аргументами
    static class ArtistCompare implements Comparator<Song> {

        @Override
        public int compare(Song o1, Song o2) {
            return o1.getArtist().compareTo(o2.getArtist());
        }
    }

    // Компаратор для реализации сортировки по названию через ArrayList и метод Sort с двумя аргументами
    static class TitleCompare implements Comparator<Song> {

        @Override
        public int compare(Song o1, Song o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }
}
