import { RouterModule, Routes } from '@angular/router';
import { HelloWorldComponent } from './components/hello-world/hello-world.component';
import { NgModule } from '@angular/core';


const routes : Routes = [
    {path:'HelloWorld', component: HelloWorldComponent}
]


@NgModule({
    imports: [RouterModule.forRoot(routes), HelloWorldComponent],
    exports:[RouterModule]
})

export class AppRoutingModule { }