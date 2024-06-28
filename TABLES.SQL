create table if not exists adresse
(
    id          serial
    primary key,
    nom_lieu    varchar(255),
    libelle     varchar(255) not null,
    ville       varchar(100) not null,
    region      varchar(100) not null,
    code_postal varchar(20)  not null
    );

alter table adresse
    owner to neondb_owner;

create table if not exists utilisateur
(
    id               serial
    primary key,
    email            varchar(255) not null
    unique,
    mot_de_passe     varchar(255) not null,
    nom              varchar(100) not null,
    prenom           varchar(100) not null,
    date_naissance   date         not null,
    adresse_id       integer      not null
    references adresse,
    centres_interets varchar(255),
    note_moyenne     double precision
    );

alter table utilisateur
    owner to neondb_owner;

create table if not exists soiree
(
    id                          serial
    primary key,
    nom                         varchar(255) not null,
    description                 text,
    date_heure                  timestamp    not null,
    adresse_id                  integer      not null
    references adresse,
    nb_places_total             integer      not null,
    nb_places_restantes         integer      not null,
    est_payante                 boolean      not null,
    prix                        numeric(10, 2),
    date_publication            timestamp    not null,
    apportez_boissons_aperitifs boolean      not null,
    organisateur_id             integer      not null
    references utilisateur,
    type_soiree                 varchar(20)  not null
    constraint soiree_type_soiree_check
    check ((type_soiree)::text = ANY
((ARRAY ['JEUX_SOCIETE'::character varying, 'JEUX_VIDEO'::character varying, 'CLASSIQUE'::character varying])::text[]))
    );

alter table soiree
    owner to neondb_owner;

create trigger creer_conversation_groupe_trigger
    after insert
    on soiree
    for each row
    execute procedure creer_conversation_groupe();

create table if not exists soireejeuxsociete
(
    soiree_id     integer not null
    primary key
    references soiree,
    jeux_proposes varchar(255)
    );

alter table soireejeuxsociete
    owner to neondb_owner;

create table if not exists soireejeuxvideo
(
    soiree_id         integer      not null
    primary key
    references soiree,
    plateforme        varchar(100) not null,
    jeux_proposes     varchar(255),
    nb_pc_disponibles integer
    );

alter table soireejeuxvideo
    owner to neondb_owner;

create table if not exists soireeclassique
(
    soiree_id integer not null
    primary key
    references soiree,
    theme     varchar(100)
    );

alter table soireeclassique
    owner to neondb_owner;

create table if not exists participation
(
    id             serial
    primary key,
    utilisateur_id integer     not null
    references utilisateur,
    soiree_id      integer     not null
    references soiree,
    statut         varchar(20) not null
    constraint participation_statut_check
    check ((statut)::text = ANY
((ARRAY ['EN_ATTENTE'::character varying, 'APPROUVEE'::character varying, 'REFUSEE'::character varying])::text[])),
    date_demande   timestamp   not null,
    date_reponse   timestamp,
    unique (utilisateur_id, soiree_id)
    );

alter table participation
    owner to neondb_owner;

create trigger ajouter_participant_conversation_groupe_trigger
    after insert or update
                        on participation
                        for each row
                        execute procedure ajouter_participant_conversation_groupe();

create table if not exists avis
(
    id             serial
    primary key,
    commentaire    text,
    note           integer   not null
    constraint avis_note_check
    check ((note >= 1) AND (note <= 5)),
    date_creation  timestamp not null,
    utilisateur_id integer   not null
    references utilisateur,
    soiree_id      integer
    references soiree,
    to_user_id     integer
    references utilisateur
    );

alter table avis
    owner to neondb_owner;

create table if not exists conversation
(
    id            serial
    primary key,
    date_creation timestamp not null,
    soiree_id     integer
    references soiree,
    est_groupe    boolean   not null
);

alter table conversation
    owner to neondb_owner;

create table if not exists message
(
    id              serial
    primary key,
    contenu         text      not null,
    date_envoi      timestamp not null,
    utilisateur_id  integer   not null
    references utilisateur,
    conversation_id integer   not null
    references conversation
);

alter table message
    owner to neondb_owner;

create table if not exists utilisateur_conversation
(
    utilisateur_id  integer not null
    references utilisateur,
    conversation_id integer not null
    references conversation,
    primary key (utilisateur_id, conversation_id)
    );

alter table utilisateur_conversation
    owner to neondb_owner;

