package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.notifyreport.*
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportReq as NotifyReportReqGen
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportResp as NotifyReportRespGen
import fr.simatix.cs.simulator.api.model.notifyreport.ReportDataType as ReportDataTypeGen
import fr.simatix.cs.simulator.api.model.notifyreport.VariableAttributeType as VariableAttributeTypeGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class NotifyReportMapper {

    @Named("convertVariableAttributeType")
    abstract fun convertVariableAttributeType(variableAttributeType: VariableAttributeTypeGen): VariableAttributeType

    @Named("convertVariableAttributeTypeList")
    fun convertVariableAttributeTypeList(variableAttributeType: List<VariableAttributeTypeGen>): List<VariableAttributeType> =
        variableAttributeType.map { convertVariableAttributeType(it) }

    @Named("convertReportData")
    @Mapping(
        target = "variableAttribute",
        source = "variableAttribute",
        qualifiedByName = ["convertVariableAttributeTypeList"]
    )
    abstract fun convertReportData(reportData: ReportDataTypeGen?): ReportDataType?

    @Mapping(target = "reportData", source = "reportData", qualifiedByName = ["convertReportData"])
    abstract fun genToCoreReq(notifyReportReq: NotifyReportReqGen?): NotifyReportReq

    abstract fun coreToGenResp(notifyReportResp: NotifyReportResp?): NotifyReportRespGen
}