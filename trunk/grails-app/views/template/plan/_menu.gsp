<div class="menuBox" id="menuBox">
    <h3>Учебный план</h3>



    <ul class="block_menu">

        <li class="${active > 1 ? 'passed' : (active == 1 ? 'active' : active < 1 ? 'next' : '')}">
            <g:if test="${plan?.id!= null}">
                <g:link action="index" controller="selectSpeciality" id="${plan.id}">Инициализация</g:link>
            </g:if>
            <g:else>
                Инициализация
            </g:else>
        </li>

        <g:if test="${plan?.subjects?.size()!=0}">
            <li class="${active > 2 ? 'passed' : (active == 2 ? 'active' : active < 2 ? 'next' : '')}">
        </g:if>
        <g:else>
            <li class="${active == 2 ? 'active' : 'next'}">
        </g:else>
        <g:if test="${plan?.id!= null}">
        <g:link controller="addSubjects" action="index" id="${plan.id}">Добавление предметов</g:link>
        </g:if>
        <g:else>
        Добавление предметов
        </g:else>
    </li>

        <g:if test="${plan?.practise?.size()!=0 }">

            <li class="${active > 3 ? 'passed' : (active == 3 ? 'active' : active < 3 ? 'next' : '')}">
        </g:if>
        <g:else>

            <li class="${active == 3 ? 'active' : 'next'}">
        </g:else>
        <g:if test="${plan?.id!= null}">
        <g:link controller="practice" action="index" id="${plan.id}">Добавление практик</g:link>
        </g:if>
        <g:else>
        Добавление практик
        </g:else>
    </li>

        <g:if test="${plan?.stateExam!=null}">
            <li class="${active > 4 ? 'passed' : (active == 4 ? 'active' : active < 4 ? 'next' : '')}">
        </g:if>
        <g:else>
            <li class="${active == 4 ? 'active' : 'next'}">
        </g:else>
        <g:if test="${plan?.id!= null}">
        <g:link controller="stateExam" action="index" id="${plan.id}">Гос.экзамен</g:link>
        </g:if>
        <g:else>
        Гос.экзамен
        </g:else>
    </li>

        <li class="${active > 5 ? 'passed' : (active == 5 ? 'active' : active < 5 ? 'next' : '')}">

            <g:if test="${plan?.id!= null && plan?.semestr!=null}">
                <g:link controller="semestr" action="index" id="${plan.id}">Семестры</g:link>
            </g:if>
            <g:else>
                Семестры
            </g:else>
        </li>

    </ul>

</div>