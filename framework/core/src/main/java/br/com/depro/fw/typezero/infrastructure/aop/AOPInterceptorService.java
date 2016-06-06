package br.com.depro.fw.typezero.infrastructure.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.depro.fw.typezero.infrastructure.annotation.Audit;
import br.com.depro.fw.typezero.infrastructure.dao.audit.AuditoriaDAO;
import br.com.depro.fw.typezero.infrastructure.model.EntidadeBase;
import br.com.depro.fw.typezero.infrastructure.model.audit.Auditoria;
import br.com.depro.fw.typezero.infrastructure.model.audit.Auditoria.EventAudit;
import br.com.depro.fw.typezero.infrastructure.model.audit.AuditoriaDetalhe;
import br.com.depro.fw.typezero.infrastructure.security.SessionBase;
import br.com.depro.fw.typezero.infrastructure.service.TypezeroGenericServiceImpl;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 15.09.2012
 */
public class AOPInterceptorService {

	@Autowired(required = false)
	private SessionBase sessionBase;
	@Autowired
	private AuditoriaDAO auditoriaDAO;
    private Logger log;

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    	long startTime = System.currentTimeMillis();
    	try {
    		EventAudit event = eventoIsAuditavel(joinPoint);
    		EntidadeBase de = buscarValorOriginal(joinPoint, event);
    		Object result = joinPoint.proceed();
			auditar(joinPoint, event, de, result);
    		gerarLinhaLog(joinPoint, startTime, result, null);
    		return result;
    	} catch (Throwable t) {
    		gerarLinhaLog(joinPoint, startTime, null, t);
    		throw t;
    	}
    }
    
	private EntidadeBase buscarValorOriginal(ProceedingJoinPoint joinPoint, EventAudit event) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
		EntidadeBase entidadeParam = null;
		for (Object argument : joinPoint.getArgs()) {
			if (argument instanceof EntidadeBase) {
				entidadeParam = (EntidadeBase) argument;
				break;
			}
		}
		if (entidadeParam != null) {
			if (event.isEditar()) {
				if (entidadeParam != null) {
					EntidadeBase entidadeOriginal = (EntidadeBase) TypezeroGenericServiceImpl.class.getMethod("buscarPorIdSimples", Long.class).invoke(joinPoint.getTarget(), entidadeParam.getId());
					EntidadeBase entidadeBase = entidadeOriginal.getClass().newInstance();
					BeanUtils.copyProperties(entidadeOriginal, entidadeBase);
					return entidadeBase;
				}
			} else if (event.isDeletar()) {
				EntidadeBase entidade = new EntidadeBase() {
					/** Numero de serie */
					private static final long serialVersionUID = -4322292121978925526L;
					@Override
					public Long getId() {
						return id;
					}
				};
				entidade.setId(entidadeParam.getId());
				return entidade;
			}
		}
		return null;
	}
    
    private EventAudit eventoIsAuditavel(ProceedingJoinPoint joinPoint) {
    	EventAudit event = EventAudit.NULL;
    	try {
	    	final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
	    	final String methodName = methodSignature.getName();
	        final Method method = joinPoint.getTarget().getClass().getSuperclass().getMethod(methodName, methodSignature.getParameterTypes());
	        
	        ArrayList<Annotation> annotations = new ArrayList<Annotation>(Arrays.asList(method.getAnnotations()));
	        
	        Audit audit = null;
	    	for (Annotation annotation : annotations) {
	    		if (annotation instanceof Audit) {
	    			audit = (Audit) annotation;
	    			break;
	    		}
	    	}
	        
	    	EntidadeBase entidadeParam = null;
	        for (Object argument : joinPoint.getArgs()) {
	        	if (argument instanceof EntidadeBase) {
	        		entidadeParam = (EntidadeBase) argument;
	        	}
	        }
	        
	    	if (audit != null && entidadeParam != null) {
	    		if (audit.eventAudit().isSalvar() && entidadeParam.getId() != null) {
	    			event = EventAudit.EDITAR;
	    		} else {
	    			event = audit.eventAudit();
	    		}
	    	}
    	} catch (NoSuchMethodException nsmexp) {
    	} 
    	return event;
    }
    
    private void auditar(ProceedingJoinPoint joinPoint, EventAudit EventAudit, EntidadeBase entidadeRequest, Object entidadeResponse) 
    		throws SecurityException, IllegalArgumentException, IllegalAccessException {
    	if (!EventAudit.isNull()) {
    		EntidadeBase entidadeParam = null;
    		if (entidadeRequest == null) {
		        for (Object argument : joinPoint.getArgs()) {
		        	if (argument instanceof EntidadeBase) {
		        		entidadeParam = (EntidadeBase) argument;
		        		break;
		        	}
		        }
    		} else {
    			entidadeParam = entidadeRequest;
    		}
	        
	    	if (entidadeResponse instanceof EntidadeBase) {
	    		Auditoria auditoria = new Auditoria();
	    		EntidadeBase entidadeWrapped = (EntidadeBase) entidadeResponse;
	    		auditoria.setEvento(EventAudit);
	    		auditoria.setIdEntidade(EventAudit.isDeletar() ? entidadeParam.getId() : entidadeWrapped.getId());
	    		auditoria.setNomeTabela(getNomeTabela(entidadeParam));
	    		if (sessionBase != null) {
		    		auditoria.setUserID(sessionBase.getUserId());
		    		auditoria.setLogin(sessionBase.getUsername());
	    		}
	    		
	    		if (!EventAudit.isDeletar()) {
		    		List<AuditoriaDetalhe> detalhes = new ArrayList<AuditoriaDetalhe>();
		    		genereteAuditoriaDetalhes(detalhes, entidadeParam, entidadeWrapped, 0, "", EventAudit.isSalvar());
		    		auditoria.setDetalhes(detalhes);
	    		}
	    		
	    		auditoriaDAO.save(auditoria);
	    	}
    	}
    }
    
    private void genereteAuditoriaDetalhes(List<AuditoriaDetalhe> detalhes, EntidadeBase entidadeRequest, EntidadeBase entidadeResponse, int nivel, String pathKey, boolean isSalvar) 
    		throws IllegalArgumentException, IllegalAccessException {
		for (Field field : entidadeRequest.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (!field.toGenericString().contains("serialVersionUID") && !Collection.class.isAssignableFrom(field.getType())) {
				Object valueRequest = field.get(entidadeRequest);
				if (valueRequest instanceof EntidadeBase && nivel < 1) {
//					genereteAuditoriaDetalhes(detalhes, entidadeRequest, entidadeResponse, nivel++, field.getName() + ".", isSalvar);
				} else {
					Object valueResponse = null;
					if (entidadeResponse != null) { 
						valueResponse = field.get(entidadeResponse);
					}
					
					if (valueRequest != null || valueResponse != null) {
						if (!EqualsBuilder.reflectionEquals(valueRequest, valueResponse) || isSalvar) {
							AuditoriaDetalhe detalhe = new AuditoriaDetalhe();
							detalhe.setChave(pathKey + field.getName());
							if (isSalvar) {
								detalhe.setValorDe(valueRequest != null ? valueRequest.toString() : valueResponse.toString());
							} else {
								detalhe.setValorDe(valueRequest != null ? valueRequest.toString() : null);
								detalhe.setValorPara(valueResponse != null ? valueResponse.toString() : null);
							}
							detalhes.add(detalhe);
						}
					}
				}
			}
		}
	}
    
    /**
     * Obtem nome da tabela mapeada na entidade
     * @param entidade
     * @return String come o nome da tabela
     */
    private String getNomeTabela(EntidadeBase entidade) {
    	for (Annotation annotation : entidade.getClass().getAnnotations()) {
    		if (annotation instanceof Table) {
    			return ((Table)annotation).name();
    		}
    	}
        return null;
    }

    
	private void gerarLinhaLog(ProceedingJoinPoint joinPoint, long startTime, Object result, Throwable exp) {
		String metodo = "".concat(joinPoint.getSignature().getDeclaringType().getSimpleName()).concat(".").concat(joinPoint.getSignature().getName()).concat(" << ");
        this.log = Logger.getLogger(joinPoint.getTarget().getClass());
        
        String params = "";
        for (Object object : joinPoint.getArgs()) {
            params += "".equals(params) ? "" : ", ";
            if (object instanceof EntidadeBase) {
                params += ((EntidadeBase) object).toString();
            } else {
                params += object != null ? object.toString() : "NULL";
            }
        }
        params += "".equals(params) ? "VOID" : "";
        params += " >> ";
        
        String message = "";
        if (result != null) {
            if (!(result instanceof Collection)) {
            	message = metodo + params;
            } else {
            	@SuppressWarnings("rawtypes")
				int size = ((Collection) result).size();
            	message = metodo + params + size + (size > 1 ? " registros" : " registro");
            }
        } else if (exp != null) {
        	message = metodo + params + " Erro: " + exp.getMessage();
        } else {
        	message = metodo + params + "VOID";
        }
        
        long duration = System.currentTimeMillis() - startTime;
        String usuario = sessionBase != null ? StringUtils.rightPad("[" + sessionBase.getUsername() + "]", 30, " ") : StringUtils.rightPad("[" + SessionBase.USUARIO_DESCONHECIDO + "]", 30, " ");
        String milesegundos = StringUtils.rightPad(duration + "ms", 10, "");
        this.log.info(usuario + milesegundos + message);
	}

}
