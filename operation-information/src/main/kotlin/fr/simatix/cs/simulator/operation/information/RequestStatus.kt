package fr.simatix.cs.simulator.operation.information

enum class RequestStatus {
    SUCCESS,
    NOT_SEND,
    FAILED;

    fun combine(status: RequestStatus): RequestStatus =
        if (this == SUCCESS || this == status){
            status
        }else if ( this == FAILED || status == FAILED) {
            FAILED
        } else{
            NOT_SEND
        }

}
