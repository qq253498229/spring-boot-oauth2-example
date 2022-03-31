import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowPersonalRoleComponent } from './show-personal-role.component';

describe('ShowPersonalRoleComponent', () => {
  let component: ShowPersonalRoleComponent;
  let fixture: ComponentFixture<ShowPersonalRoleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowPersonalRoleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowPersonalRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
