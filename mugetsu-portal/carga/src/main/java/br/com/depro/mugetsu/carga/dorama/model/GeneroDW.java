package br.com.depro.mugetsu.carga.dorama.model;

import br.com.depro.mugetsu.model.media.vo.GeneroEnum;

public enum GeneroDW {

    Detective                        (GeneroEnum.POLICE),
    Suspence                         (GeneroEnum.MISTERIO),
    psychological                    (GeneroEnum.PSY),
    Ancient_legend                   (GeneroEnum.HIS),
    magic                            (GeneroEnum.MAGIA),
    College_romance                  (GeneroEnum.ROMANCE),
    Sport                            (GeneroEnum.ESPORTE),
    Love                             (GeneroEnum.ROMANCE),
    Suspense                         (GeneroEnum.MISTERIO),
    Action_idol_drama                (GeneroEnum.ACAO),
    Melodrama                        (GeneroEnum.ROMANCE),
    Contemporary_Romance             (GeneroEnum.ROMANCE),
    Comedy                           (GeneroEnum.COMEDIA),
    Action_drama                     (GeneroEnum.ACAO),
    Historical_Romance               (GeneroEnum.HIS),
    fantasy                          (GeneroEnum.FANTASIA),
    adventure                        (GeneroEnum.AVENTURA),
    Action                           (GeneroEnum.ACAO),
    with_some_supernatural_content   (GeneroEnum.SUPERN),
    Fantasy                          (GeneroEnum.FANTASIA),
    Military                         (GeneroEnum.MILITAR),
    Horse_racing                     (GeneroEnum.ESPORTE),
    Historical_fiction               (GeneroEnum.HIS),
    Romance                          (GeneroEnum.ROMANCE),
    Ghost_story                      (GeneroEnum.SUPERN),
    Police_Drama                     (GeneroEnum.POLICE),
    suspense_drama                   (GeneroEnum.MISTERIO),
    College                          (GeneroEnum.SLL),
    history                          (GeneroEnum.HIS),
    police_drama                     (GeneroEnum.POLICE),
    Ghost                            (GeneroEnum.SUPERN),
    Romantic_saga                    (GeneroEnum.ROMANCE),
    Supernatural                     (GeneroEnum.SUPERN),
    Army                             (GeneroEnum.MILITAR),
    psychiatry                       (GeneroEnum.PSY),
    time_travel                      (GeneroEnum.FICC),
    sports                           (GeneroEnum.ESPORTE),
    Crime_Investigation              (GeneroEnum.POLICE),
    Sports                           (GeneroEnum.ESPORTE),
    High_School                      (GeneroEnum.SLL),
    Basketball                       (GeneroEnum.ESPORTE),
    Musical                          (GeneroEnum.MUSICA),
    Investigation                    (GeneroEnum.POLICE),
    Romantic                         (GeneroEnum.ROMANCE),
    Police_drama                     (GeneroEnum.POLICE),
    Incest_romance_renzoku           (GeneroEnum.ECCHI),
    scifi                            (GeneroEnum.FICC),
    romance                          (GeneroEnum.ROMANCE),
    horse_racing                     (GeneroEnum.ESPORTE),
    History                          (GeneroEnum.HIS),
    music                            (GeneroEnum.MUSICA),
    Romantic_comedy                  (GeneroEnum.ROMANCE),
    school                           (GeneroEnum.SLL),
    historical                       (GeneroEnum.HIS),
    melodrama                        (GeneroEnum.ROMANCE),
    Crime                            (GeneroEnum.POLICE),
    Mystery                          (GeneroEnum.MISTERIO),
    drama                            (GeneroEnum.DRAMA),
    Police_Comedy                    (GeneroEnum.COMEDIA),
    Romanticized_historical_drama    (GeneroEnum.ROMANCE),
    Sports_drama                     (GeneroEnum.ESPORTE),
    suspense                         (GeneroEnum.MISTERIO),
    Office_Romance                   (GeneroEnum.ROMANCE),
    Fiction                          (GeneroEnum.FICC),
    science_fiction                  (GeneroEnum.FICC),
    School_drama                     (GeneroEnum.DRAMA),
    comedy                           (GeneroEnum.COMEDIA),
    Historical_drama                 (GeneroEnum.HIS),
    War                              (GeneroEnum.MILITAR),
    fiction                          (GeneroEnum.FICC),
    Police                           (GeneroEnum.POLICE),
    Scifi                            (GeneroEnum.FICC),
    Horror                           (GeneroEnum.HOR),
    Magic                            (GeneroEnum.MAGIA),
    Romance_drama                    (GeneroEnum.ROMANCE),
    Romantic_drama                   (GeneroEnum.ROMANCE),
    home_drama                       (GeneroEnum.DRAMA),
    horror                           (GeneroEnum.HOR),
    detective                        (GeneroEnum.POLICE),
    Drama                            (GeneroEnum.DRAMA),
    Daeha_drama                      (GeneroEnum.DRAMA),
    Modern_societal_comedy           (GeneroEnum.COMEDIA),
    mystery                          (GeneroEnum.MISTERIO),
    Historial                        (GeneroEnum.HIS),
    Kids_drama                       (GeneroEnum.DRAMA),
    police                           (GeneroEnum.POLICE),
    Music                            (GeneroEnum.MUSICA),
    action                           (GeneroEnum.ACAO),
    investigation                    (GeneroEnum.POLICE),
    School                           (GeneroEnum.SLL),
    home_comedy                      (GeneroEnum.COMEDIA),
    musical                          (GeneroEnum.MUSICA),
    human_drama                      (GeneroEnum.DRAMA),
    Home_drama                       (GeneroEnum.DRAMA),
    Modern_comedy                    (GeneroEnum.COMEDIA),
    Youth_drama                      (GeneroEnum.DRAMA),
    Human_drama                      (GeneroEnum.DRAMA),
    Culinary_comedy                  (GeneroEnum.COMEDIA),
    war                              (GeneroEnum.MILITAR),
    High_school_romance              (GeneroEnum.ROMANCE),
    Historical_Drama                 (GeneroEnum.HIS),
    Science_fiction                  (GeneroEnum.FICC),
    Historical_tanpatsu_drama        (GeneroEnum.HIS),
    sport                            (GeneroEnum.ESPORTE),
    Comedy_and_Period                (GeneroEnum.COMEDIA),
    Historical                       (GeneroEnum.HIS),
    Adventure                        (GeneroEnum.AVENTURA),
    supernatural                     (GeneroEnum.SUPERN),
    romantic_comedy                  (GeneroEnum.ROMANCE);

    private GeneroEnum genero;

    /**
     * @param genero
     */
    private GeneroDW(GeneroEnum genero) {
        this.genero = genero;
    }

    /**
     * @return the genero
     */
    public GeneroEnum getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }
}
