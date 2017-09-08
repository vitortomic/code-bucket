CREATE TABLE SurveyAnswer (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    answerText VARCHAR(255),
    isActive BIT,
    question_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE SurveyQuestion (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    active BIT,
    hasOtherTextResponse BIT,
    isMultiselect BIT,
    questionText VARCHAR(255),
    group_id BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE SurveyQuestionGroup (
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    type VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE UserSurveyAnswer (
    DTYPE VARCHAR(31) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    textAnswer VARCHAR(255),
    question_id BIGINT,
    answer_id BIGINT,
    user_id BIGINT,
    jobRequest_id BIGINT,
    jobRequestDay_id BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE UserSurveyAnswer (
    DTYPE VARCHAR(31) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
    textAnswer VARCHAR(255),
    question_id BIGINT,
    user_id BIGINT,
    jobRequest_id BIGINT,
    jobRequestDay_id BIGINT,
    PRIMARY KEY (id)
);
CREATE TABLE UserSurveyAnswer_SurveyAnswer (
    UserSurveyAnswer_id BIGINT NOT NULL,
    selectedAnswers_id BIGINT NOT NULL
);
alter table SurveyAnswer add index FK1D35BBD8EA7F4998 (question_id), add constraint FK1D35BBD8EA7F4998 foreign key (question_id) references SurveyQuestion (id);
alter table SurveyQuestion add index FK7EFF1980871DEA36 (group_id), add constraint FK7EFF1980871DEA36 foreign key (group_id) references SurveyQuestionGroup (id);
alter table UserSurveyAnswer add index FKB30E9343894980D6 (jobRequestDay_id), add constraint FKB30E9343894980D6 foreign key (jobRequestDay_id) references JobRequestDay (id);
alter table UserSurveyAnswer add index FKB30E93439BA08E7E (jobRequest_id), add constraint FKB30E93439BA08E7E foreign key (jobRequest_id) references JobRequest (id);
alter table UserSurveyAnswer add index FKB30E934347140EFE (user_id), add constraint FKB30E934347140EFE foreign key (user_id) references User (id);
alter table UserSurveyAnswer add index FKB30E9343EA7F4998 (question_id), add constraint FKB30E9343EA7F4998 foreign key (question_id) references SurveyQuestion (id);
alter table UserSurveyAnswer_SurveyAnswer add index FK4B5937543B1CCEDC (selectedAnswers_id), add constraint FK4B5937543B1CCEDC foreign key (selectedAnswers_id) references SurveyAnswer (id);
alter table UserSurveyAnswer_SurveyAnswer add index FK4B59375431DC23E (UserSurveyAnswer_id), add constraint FK4B59375431DC23E foreign key (UserSurveyAnswer_id) references UserSurveyAnswer (id);
