import {Component, OnInit} from '@angular/core';
import {interval, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ModelBox} from "./ModelBox";
import {PositionCar} from "./PositionCar";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'st2020';
  host = 'http://localhost:8080/app/'
  info: any;
  modelBox: ModelBox = new ModelBox();
  modelsBoxes: ModelBox[]
  x_position: string
  z_position: string
  x_start_position: number = 200;
  z_start_position: number = 70;
  positionCar: PositionCar
  positionCarTwo: PositionCar;
  x_start_position2: number = 200;
  z_start_position2: number = 70;
  carOneWithBoard: boolean
  carTwoWithBoard: boolean


  constructor(private http: HttpClient) {
    // this.getOldPositionCarForStartInit().subscribe(car=>this.positionCar=car)
    this.getDirectionCar(this.z_start_position, this.x_start_position).subscribe(car => {
      this.positionCar = car
    })

    this.getDirectionCar2(this.z_start_position2, this.x_start_position2).subscribe(car => {
      this.positionCarTwo = car
    })

  }



  ngOnInit(): void {
    this.getModel(5).subscribe(modelBox => this.modelBox = modelBox.valueOf())


    interval(3000).subscribe(
      x => this.getAllModel().subscribe(models => this.modelsBoxes = models.valueOf())
    )

    // interval(1000).subscribe(
    //   x=>
    // )


  }


  getModel(idBox: number): Observable<any> {
    return this.http.get(this.host + 'box/' + idBox)
  }


  getAllModel(): Observable<any> {
    return this.http.get(this.host + 'boxes')
  }

  getDirectionCar(z: number, x: number): Observable<any> {
    return this.http.get(this.host + 'car/' + z + '/' + x)
  }
  getDirectionCar2(z: number, x: number): Observable<any> {
    return this.http.get(this.host + 'car2/' + z + '/' + x)
  }

  count = 0
  x_direction: string;


  setStartPositionX() {

    interval(200).subscribe(x =>
      this.getDirectionCar(this.z_start_position, this.x_start_position).subscribe(car => {
        this.positionCar = car
      })
    )
  }
  setStartPositionX2() {
    interval(200).subscribe(x =>
      this.getDirectionCar2(this.z_start_position2, this.x_start_position2).subscribe(car => {
        this.positionCarTwo = car
      })
    )
  }

  getOldPositionCarForStartInit(): Observable<any> {
    return this.http.get(this.host + 'car/init')
  }


  addModelNew() {
    this.x_position = this.positionCar.x_position
    this.z_position = this.positionCar.z_position
    this.http.get(this.host + 'add/' + this.z_position + '/' + this.x_position).subscribe()
    console.log('add' + this.positionCar.z_position + '/' + this.positionCar.x_position)
    this.z_start_position = 70
    this.carOneWithBoard=false
  }





  resetCount() {
    console.log("reset count")
    this.count = 0

  }

  carOneWithBoardSTATE() {
    if (this.carOneWithBoard != true)
      this.carOneWithBoard = true
    else this.carOneWithBoard = false

    console.log(this.carOneWithBoard)
  }

  carTwoWithBoardSTATE() {
    if (this.carTwoWithBoard != true)
      this.carTwoWithBoard = true
    else this.carTwoWithBoard = false
  }


  private deleteBoardUnderCar2():Observable<any>{
  return   this.http.get(this.host+'car2/found/board')
  }

  getModelNew() {
    this.deleteBoardUnderCar2().subscribe(board=>this.carTwoWithBoard=board.valueOf())
    this.z_start_position2 = 700
  }
}
