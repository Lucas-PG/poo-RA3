Package entidades
Medico, Paciente, Consulta

Talvez a gente tenha que pegar aquele método estático de pegar todos os códigos de doutor e usar isso no construtor pra não deixar registrar um médico com o mesmo código que o outro
E fazer isso com os pacientes tmb

Classe reader
pegar o arquivo
ler o arquivo
colocar nos objetos
retornar a lista de objetos

Classe interface
mostrar a mensagem
retornar a opção escolhida
executar o método que ele pediu

Escolhas (\* considerar consultas após o dia atual)
getPatients(Medico medico) M
getAppointmentsByPeriod(Medico medico, LocalDate startDate, LocalDate endDate) C
getDoctorsByPatient(Paciente paciente) P
getAppointmentsByDoctor(Paciente paciente, Medico medico) C
getAppointmentsByPatient(Paciente paciente) C
getMissingPatients(Medico medico, int months) M

App
ler do arquivo
guardar os dados
mostrar interface de escolhas
executar cada um dos medicos

Escrever as classes entidades
Escrever a classe interface inteira
Implementar a classe interface no App
Escrever a classe reader
Implementar reader no App
Partir pras escolhas
